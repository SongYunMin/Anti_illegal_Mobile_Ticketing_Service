package com.example.moblic_ticketing_service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.biometrics.BiometricPrompt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.moblic_ticketing_service.Ticketing_Activity.Concert_Information;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : Main_Activity 에서 "내 티켓" 메뉴를 클릭했을때 실행되는 Activity
        필요사항 : 2019-09-14-02시56분) : 티켓 마다 동적 출력 해야 함 ***********************************완료
                  2019-09-14-02시58분) : "활성화" 버튼 생성하고 클릭시 지문인증 화면 출력해야 함
        오류사항 :
        수정사항 : 2019-09-15-00시29분) : 티켓 동적 생성 완료
                  2019-09-15-00시42분) : 활성화 버튼 생성 완료
                  2019-09-15-19시49분) : 예약이 되지 않았다면, 텍스트 출력과 예약되는 Activity로 이동하게 수정
*/

public class My_Ticket_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__ticket_);

        // 티켓이 예매가 되어있는 상태에서 My_Ticket_Activity 에 출력할 ImageView,TextView,Button
        final ImageView IMG_Ticket = (ImageView) findViewById(R.id.IMG_Ticket);
        final TextView Text_Ticket = (TextView) findViewById(R.id.Text_Ticket);
        final Button Button_Activation = (Button)findViewById(R.id.Activation);

        // 티켓이 에매가 되어있지 않은 상태에서 My_Ticket_Activity에 출력할 TextView,Button
        final TextView N_Reservation_text_1 = (TextView) findViewById(R.id.N_Reservation_text_1);
        final TextView N_Reservation_text_2 = (TextView) findViewById(R.id.N_Reservation_text_2);
        final TextView N_Reservation_Button = (TextView) findViewById(R.id.N_Reservation_Button);

        // BTS 공연을 예약 했을 경우 BTS 공연 정보 출력
        if(Concert_Information == 1){
            IMG_Ticket.setImageResource(R.drawable.bts);
            Text_Ticket.setText("BTS World TOUR");
            Button_Activation.setVisibility(View.VISIBLE);
        }
        // 김경호 공연을 예약 했을 경우 김경호 공연 정보 출력
        else if(Concert_Information == 2){
            IMG_Ticket.setImageResource(R.drawable.kkh);
            Text_Ticket.setText("2019 KimKyungHo");
            Button_Activation.setVisibility(View.VISIBLE);
        }
        // 서강준 팬미팅을 예약 했을 경우 서강준 팬미팅 정보 출력
        else if(Concert_Information == 3){
            IMG_Ticket.setImageResource(R.drawable.skj);
            Text_Ticket.setText("SEO KANG JUN : Me Befo...");
            Button_Activation.setVisibility(View.VISIBLE);
        }
        // 맘마미아 뮤지컬을 예약 했을 경우 맘마미아 뮤지컬 정보 출력
        else if(Concert_Information == 4){
            IMG_Ticket.setImageResource(R.drawable.mmmia);
            Text_Ticket.setText("Mamma Mia!");
            Button_Activation.setVisibility(View.VISIBLE);
        }
        // 티켓이 예약된 상태가 아니였을 경우, 텍스트 출력과 Ticketing_Activity로 이동하는 버튼 생성
        else {
            N_Reservation_text_1.setVisibility(View.VISIBLE);
            N_Reservation_text_2.setVisibility(View.VISIBLE);
            N_Reservation_Button.setVisibility(View.VISIBLE);
            N_Reservation_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Ticketing_Activity.class);
                    startActivity(intent);
                }
            });
        }
    }


    public void Activation(View v){
        AlertDialog.Builder Alert_Ticketing = new AlertDialog.
                Builder(My_Ticket_Activity.this);
        Alert_Ticketing.setTitle("티켓 활성화")
                .setMessage("티켓을 활성화 하시겠습니까?\n 60초간 QR코드가 활성화 됩니다.")
                // '취소'를 눌렀을 때에 이벤트
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                // '예약'을 눌렀을 때에 이벤트
                .setPositiveButton("활성화", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Alert_Ticketing, int which) {
                        Intent intent = new Intent(getApplicationContext(), FingerPrint_Activity.class);
                        startActivity(intent);
                    }
                });
        Alert_Ticketing.setCancelable(false);
        AlertDialog dialog = Alert_Ticketing.create();
        dialog.show();
    }
}
