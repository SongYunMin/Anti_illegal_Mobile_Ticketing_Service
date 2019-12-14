package com.example.moblic_ticketing_service;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : MainActivity 에서 "로그인" 메뉴를 눌렀을때 바로 실행되는 Activity
        필요사항 : 2019-09-14-02시23분) : 로그인이 되었을때 다른 Activity 에서 Login 된것을 알 수 있는 변수 필요함
        오류사항 : "로그인" 버튼 두번 눌러야 로그인 됨, 빠른 수정 요망
        수정사항 : 2019-09-14-02시38분) : 로그인변수 생성, "Context" 사용해서 Check 변수 전역에서 사용 가능

*/


public class LoginActivity extends AppCompatActivity {

    EditText ed_id, ed_pw;
    // St_id 가 전역에 접근해야 함
    public static String St_id;
    String St_pw;
    public static String LoginCheck;
    public static Context Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //XML 코드에서 ID/PW 를 불러옴
        ed_id = (EditText) findViewById(R.id.id_Edit);
        ed_pw = (EditText) findViewById(R.id.password_Edit);
    }

    // "로그인" 버튼을 누르면 로그인
    public void bt_Login(View v) {
        Button Login = (Button) findViewById(R.id.submit);

        // ID, PW 를 String 변수에 저장
        St_id = ed_id.getText().toString();
        St_pw = ed_pw.getText().toString();
//127.0.0.1
        HttpConnectThread http = new HttpConnectThread("http://52.231.77.29/Android_Check.php",
                "memberID=" + St_id + "&memberPw=" + St_pw);
        http.start();
        String result = http.GetResult();

        /*
        while (result.equals("")) {
            result = http.GetResult();
        }
        */

        // result 변수에 있는 값이 "False" 라면 로그인 실패
        if (result.equals("False\n")) {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
        }
        // result 변수에 있는 값이 "False" 가 아니라면 로그인 성공
        else {
            LoginCheck = result;
            Check = this;

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
