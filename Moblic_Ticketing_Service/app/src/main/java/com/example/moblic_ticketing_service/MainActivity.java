package com.example.moblic_ticketing_service;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.support.v7.app.AlertDialog;
import androidx.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// 헐 이렇게 불러올수 있었어 ;;
import static com.example.moblic_ticketing_service.LoginActivity.St_id;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : App 실행시 바로 보이는 Activity
        필요사항 : 2019-09-14-02시15분) : 로그인이 성공했다면, "로그인" 메뉴는 사용 불가 해야함, 또한
                                         티켓예매, 내 티켓 메뉴는 로그인이 되었을때만 사용 해야 함 -- 적용 완료
                  2019-09-14-20시30분) : 로그인 식별자 필요 -- 적용 완료
        오류사항 :
        수정사항 : 2019-09-14-02시26분) : "로그인" 메뉴는 로그인이 완료되었을 때 사용 할 수 없게 변경
                                           (LoginActivity 에서 받아온 Context 변수 사용함)
                  2019-09-14-20시55분) : 로그인 식별자 적용 완료
                  2019-09-15-09시15분) : 로그인시 "로그인" 버튼 사라지고 그 자리에 로그아웃 버튼 생성
                  2019-09-15-20시51분) : 로그아웃 버튼 눌렀을시 St_id(아이디 값) "null" 값 대입, 로그아웃 구현 완료
                  2019-10-14-01시09분) : Method 재정의 (Main)
*/

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 메인메뉴 객체 생성
        Button new_Account_button = (Button) findViewById(R.id.newAccount);
        final Button Login_button = (Button) findViewById(R.id.Login);
        Button Logout_button = (Button) findViewById(R.id.Logout);
        Button Ticketing_button = (Button) findViewById(R.id.Ticketing);
        Button MyTicket_button = (Button) findViewById(R.id.myTicket);

        // 로그인이 완료 되었을때 MainActivity 왼쪽 상단에 로그인된 아이디가 출력되는 식별자
        TextView LoginComplet = findViewById(R.id.LoginCompletion);

        // 로그인이 완료 되었을 떄 MainActivity 좌측 상단에 표시되는 TextView
        if (St_id != null) {
            LoginComplet.setText(St_id + "님 환영합니다");
            LoginComplet.setVisibility(View.VISIBLE);
            // 로그인 버튼은 숨기고 로그아웃 버튼 생성 되게함
            Login_button.setVisibility(View.GONE);
            Logout_button.setVisibility(View.VISIBLE);
        }
        else{
            Logout_button.setVisibility(View.GONE);
            Login_button.setVisibility(View.VISIBLE);
        }


        // 버튼 클릭시 회원가입 창으로 이동되는 코드 Intent를 이용함
        new_Account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewAccount_Activity.class);
                startActivity(intent);
            }
        });

        // 버튼 클릭시 로그인 창으로 이동되는 코드 Intent를 이용함 (로그인시 진입 못하게 수정)
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (St_id == null) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "이미 로그인 되어있습니다.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // 버튼 클릭시 로그아웃 창으로 이동되는 코드 Intent를 이용함 (로그인시 진입 못하게 수정)
        Logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (St_id != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("로그아웃")
                            .setMessage("정말 로그아웃 하시겠습니까?")
                            .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            // "인증" 버튼을 누르면 FingerPrint_Activity 로 이동한다
                            .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
                                    St_id = null;
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);

                                }
                            });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    Toast.makeText(getApplicationContext(), "이미 로그인 되어있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // 버튼 클릭시 티켓팅 창으로 이동되는 코드 Intent를 이용함
        Ticketing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (St_id != null) {
                    Intent intent = new Intent(getApplicationContext(), Ticketing_Activity.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("로그인")
                            .setMessage("로그인 후 이용 가능합니다.\n로그인 하시겠습니까?")
                            .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            // "인증" 버튼을 누르면 FingerPrint_Activity 로 이동한다
                            .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    Toast.makeText(getApplicationContext(), "로그인 후 이용해 주십시오", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 버튼 클릭시 내 티켓 창으로 이동되는 코드 Intent를 이용함
        MyTicket_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (St_id != null) {
                    Intent intent = new Intent(getApplicationContext(), My_Ticket_Activity.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("로그인")
                            .setMessage("로그인 후 이용 가능합니다.\n로그인 하시겠습니까?")
                            .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            // "인증" 버튼을 누르면 FingerPrint_Activity 로 이동한다
                            .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    Toast.makeText(getApplicationContext(), "로그인 후 이용해 주십시오", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
