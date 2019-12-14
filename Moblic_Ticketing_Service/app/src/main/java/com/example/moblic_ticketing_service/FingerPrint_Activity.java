package com.example.moblic_ticketing_service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : 지문인증을 구현하기 위한 Activity
        필요사항 : Activity의 정확한 이해
                  회원가입, 활성화 다음 Activity로 넘어가는 과정 달라야함
        오류사항 : 2019-09-16-00시06분) : 지문인증 Activity에서 지문인증을 하지 않아도 QR코드 Activity로 넘어감

        수정사항 : 2019-09-15-19시26분) : 지문 인증 후, 자동으로 QR_BARCODE_Activity로 이동
*/

public class FingerPrint_Activity extends AppCompatActivity {

    ImageView iv_fingerprint;
    TextView tv_message;
    LinearLayout linearLayout;

    public Handler mFingerPrintSecceseHandle;

    public  static FingerPrint_Activity fingerPrint_activity;

    private static final String KEY_NAME = "example_key";
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_finger_print__acticvity);


        iv_fingerprint = (ImageView) findViewById(R.id.iv_finger);
        tv_message = (TextView) findViewById(R.id.tv_message);
        linearLayout = (LinearLayout) findViewById(R.id.ll_secure);
        linearLayout.setVisibility(LinearLayout.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {//Manifest에 Fingerprint 퍼미션을 추가해 둬야 사용가능
                tv_message.setText("지문을 사용할 수 없는 디바이스 입니다.");
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)
                    != PackageManager.PERMISSION_GRANTED) {
                tv_message.setText("지문사용을 허용해 주세요.");
                /*잠금화면 상태를 체크한다.*/
            } else if (!keyguardManager.isKeyguardSecure()) {
                tv_message.setText("잠금화면을 설정해 주세요.");
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                tv_message.setText("등록된 지문이 없습니다.");
            } else {//모든 관문을 성공적으로 통과(지문인식을 지원하고 지문 사용이 허용되어 있고 잠금화면이 설정되었고 지문이 등록되어 있을때)
                tv_message.setText("손가락을 홈버튼에 대 주세요.");

                generateKey();
                if (cipherInit()) {
                    cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    //핸들러실행
                    FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                    fingerprintHandler.startAutho(fingerprintManager, cryptoObject);

                    mFingerPrintSecceseHandle = new Handler(){

                    };

                    /*
                    // 지문 인증 후, 3초뒤에 자동으로 QR_BARCODE_Activity로 넘어가는 코드
                    mFingerPrintSecceseHandle = new Handler() {
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            startActivity(new Intent(FingerPrint_Activity.this, QR_BARCODE_Activity.class));
                            Toast.makeText(getApplicationContext(), "지문인증이 완료되었습니다.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    };
                    */
                }
            }
        }
    }

    //Cipher Init()
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }
        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    //Key Generator
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}






