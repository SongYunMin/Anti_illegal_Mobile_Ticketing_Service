package com.example.moblic_ticketing_service;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : 지문인증을 구현하기 위한 Activity_Handler
        필요사항 : Activity의 정확한 이해
        오류사항 :
        수정사항 :
*/

@TargetApi(Build.VERSION_CODES.M)//이녀석은 또 처음 보는군.
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    CancellationSignal cancellationSignal;
    private Context context;

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    //메소드들 정의
    public void startAutho(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("인증 에러 발생" + errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("인증 실패", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error: " + helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("인증에 성공하였습니다", true);
        FingerPrint_Activity.fingerPrint_activity.mFingerPrintSecceseHandle.sendEmptyMessage(0);
}

    public void stopFingerAuth() {
        if (cancellationSignal != null && !cancellationSignal.isCanceled()) {
            cancellationSignal.cancel();
        }
    }
    private void update(String s, boolean b) {
        final TextView tv_message = (TextView) ((Activity) context).findViewById(R.id.tv_message);
        final ImageView iv_fingerprint = (ImageView) ((Activity) context).findViewById(R.id.iv_finger);
        final LinearLayout linearLayout = (LinearLayout) ((Activity) context).findViewById(R.id.ll_secure);

        //안내 메세지 출력
        tv_message.setText(s);

        if (b == false) {
            tv_message.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
        else {//지문인증 성공
            tv_message.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            iv_fingerprint.setImageResource(R.mipmap.ic_fingerprint);
            linearLayout.setVisibility(LinearLayout.VISIBLE);

            //sound effect
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone((Activity) context, notification);
            r.play();
        }
    }
}