package com.example.moblic_ticketing_service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : MainActivity 에서 "티켓예메" 메뉴를 눌렀을때 바로 실행되는 Activity (여러컨텐츠가 출력됨)
        필요사항 : 2019-09-14-02시34분) : LoginActivity 안에 있는 Login 변수가 "NULL" 이 아닐때만 실행 되어야 함
                                          (로그인 되었을 때에만 실행 되어야 함)
        오류사항 :
        수정사항 : 2019-09-14-22시12분) : 공연 정보들의 아이디를 불러오고 그림을 선택할때마다 각각 다른 함수들이 선택되어 실행됨
                                         (효율성이 좀 떨어짐)
*/

public class Ticketing_Activity extends AppCompatActivity {

    //public static 을 선언해야 다른 곳에서 쓸 수 있다.
    public static int Concert_Information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing_);

        /*
        // 공연들의 Id값을 불러옴
        final ImageView Ticketing_1 = (ImageView) findViewById(R.id.TicketIng_1);
        final ImageView Ticketing_2 = (ImageView) findViewById(R.id.TicketIng_2);
        final ImageView Ticketing_3 = (ImageView) findViewById(R.id.TicketIng_3);
        final ImageView Ticketing_4 = (ImageView) findViewById(R.id.TicketIng_4);
        */

    }
    // TicketImg_1을 클릭했을때에 동작 수행
    public void IMG_BTS(View v) {
        Concert_Information = 1;
        AlertDialog.Builder Alert_Ticketing = new AlertDialog.
                Builder(Ticketing_Activity.this);
        Alert_Ticketing.setTitle("티켓예매")
                .setMessage("티켓을 예매하시겠습니까?")
                // '취소'를 눌렀을 때에 이벤트
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                // '예약'을 눌렀을 때에 이벤트
                .setPositiveButton("예약", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Alert_Ticketing, int which) {
                        Intent intent = new Intent(getApplicationContext(), Ticket_Activation_Activity.class);
                        startActivity(intent);
                    }
                });
        Alert_Ticketing.setCancelable(false);
        AlertDialog dialog = Alert_Ticketing.create();
        dialog.show();
    }

    // TicketImg_2을 클릭했을때에 동작 수행
    public void IMG_KKH(View v) {
        Concert_Information = 2;
        AlertDialog.Builder Alert_Ticketing = new AlertDialog.
                Builder(Ticketing_Activity.this);
        Alert_Ticketing.setTitle("티켓예매")
                .setMessage("티켓을 예매하시겠습니까?")
                // '취소'를 눌렀을 때에 이벤트
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                // '예약'을 눌렀을 때에 이벤트
                .setPositiveButton("예약", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Alert_Ticketing, int which) {
                        Intent intent = new Intent(getApplicationContext(), Ticket_Activation_Activity.class);
                        startActivity(intent);
                    }
                });
        Alert_Ticketing.setCancelable(false);
        AlertDialog dialog = Alert_Ticketing.create();
        dialog.show();
    }

    // TicketImg_3을 클릭했을때에 동작 수행
    public void IMG_SKJ(View v) {
        Concert_Information = 3;
        AlertDialog.Builder Alert_Ticketing = new AlertDialog.
                Builder(Ticketing_Activity.this);
        Alert_Ticketing.setTitle("티켓예매")
                .setMessage("티켓을 예매하시겠습니까?")
                // '취소'를 눌렀을 때에 이벤트
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                // '예약'을 눌렀을 때에 이벤트
                .setPositiveButton("예약", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Alert_Ticketing, int which) {
                        Intent intent = new Intent(getApplicationContext(), Ticket_Activation_Activity.class);
                        startActivity(intent);
                    }
                });
        Alert_Ticketing.setCancelable(false);
        AlertDialog dialog = Alert_Ticketing.create();
        dialog.show();
    }

    // TicketImg_4을 클릭했을때에 동작 수행
    public void IMG_Mamma_MIA(View v) {
        Concert_Information = 4;
        AlertDialog.Builder Alert_Ticketing = new AlertDialog.
                Builder(Ticketing_Activity.this);
        Alert_Ticketing.setTitle("티켓예매")
                .setMessage("티켓을 예매하시겠습니까?")
                // '취소'를 눌렀을 때에 이벤트
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                // '예약'을 눌렀을 때에 이벤트
                .setPositiveButton("예약", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Alert_Ticketing, int which) {
                        Intent intent = new Intent(getApplicationContext(), Ticket_Activation_Activity.class);
                        startActivity(intent);
                    }
                });
        Alert_Ticketing.setCancelable(false);
        AlertDialog dialog = Alert_Ticketing.create();
        dialog.show();
    }
}


