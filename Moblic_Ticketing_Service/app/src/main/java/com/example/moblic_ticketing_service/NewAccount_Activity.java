package com.example.moblic_ticketing_service;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : Main 에서 "회원가입" 버튼을 누르면 실행되는 Activity
        필요사항 : 2019-09-14-02시50분) : Device의 해상도마다 출력의 차이가 없어야 함
        오류사항 :
        수정사항 :
*/

public class NewAccount_Activity extends AppCompatActivity {

    EditText ed_name, ed_id, ed_pw, ed_pw2, ed_phone_1, ed_phone_2, ed_phone_3, ed_address;
    RadioButton ed_sex;
    String St_name, St_id, St_pw, St_pw2, St_phone_1, St_phone_2, St_phone_3, St_sex, St_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);
        // XML 코드에서 입력한 값들을 불러옴
        ed_name = (EditText) findViewById(R.id.name_Edit);
        ed_id = (EditText) findViewById(R.id.id_Edit);
        ed_pw = (EditText) findViewById(R.id.pw_Edit);
        ed_pw2 = (EditText) findViewById(R.id.pw2_Edit);
        ed_phone_1 = (EditText) findViewById(R.id.phone_Edit_1);
        ed_phone_2 = (EditText) findViewById(R.id.phone_Edit_2);
        ed_phone_3 = (EditText) findViewById(R.id.phone_Edit_3);
        ed_sex = (RadioButton) findViewById(R.id.Sex_Man);
        ed_sex = (RadioButton) findViewById(R.id.Sex_women);
        ed_address = (EditText) findViewById(R.id.address_Edit);

    }

    // "회원가입" 버튼을 누르면 데이터를 전송함
    public void bt_join(View view) {

        Button Join = (Button) findViewById(R.id.submit);

        St_name = ed_name.getText().toString();
        St_id = ed_id.getText().toString();
        St_pw = ed_pw.getText().toString();
        St_pw2 = ed_pw2.getText().toString();
        St_phone_1 = ed_phone_1.getText().toString();
        St_phone_2 = ed_phone_2.getText().toString();
        St_phone_3 = ed_phone_3.getText().toString();
        St_sex = ed_sex.getText().toString();
        St_address = ed_address.getText().toString();

        //비밀번호와 비밀번호 확인이 일치하다면 DB에 정보들을 저장
        if (St_pw.equals(St_pw2)) {
            //String 형으로 입력받은 코드들을 서버로 전송
            HttpConnectThread http = new HttpConnectThread(
                    "http://52.231.77.29/JOINmember.php",
                    "userid=" + St_id + "&username=" + St_name + "&userpassword=" + St_pw + "&m1=" + St_phone_1 +
                            "&m2=" + St_phone_2 + "&m3=" + St_phone_3 + "&SEX=" + St_sex + "&address=" + St_address);
            http.start();
            String temp = http.GetResult();

            // "회원가입" 버튼을 눌렀을때 이벤트 처리
            Join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast 메세지
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다", Toast.LENGTH_LONG).show();
                    //알림 창
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewAccount_Activity.this);
                    builder.setTitle("지문인증요청")
                            .setMessage("안전한 티켓 거래를 위해 지문인증이 필요합니다. 지금 인증하시겠습니까?")
                            .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            // "인증" 버튼을 누르면 FingerPrint_Activity 로 이동한다
                            .setPositiveButton("인증", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), FingerPrint_Activity.class);
                                    startActivity(intent);
                                }
                            });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                   /* //이 코드는 회원가입 버튼을 눌렀을때 메인액티비티로 이동되는 코드임.
                   Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                   */

                }
            });
        }
        // 같지 않다면 Toast를 띄우고 다시입력
        else {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다",
                    Toast.LENGTH_LONG).show();
        }
    }
}