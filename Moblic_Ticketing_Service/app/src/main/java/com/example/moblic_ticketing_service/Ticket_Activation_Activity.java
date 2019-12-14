package com.example.moblic_ticketing_service;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.moblic_ticketing_service.Ticketing_Activity.Concert_Information;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : Ticket_Activity 에서 "예약" 버튼을 누르면 실행되는 Activity
        필요사항 : 2019-09-14-02시55분) : Ticket_Activity와 마찬가지로 여러 컨텐츠가 있는데 동적으로 공연이 출력 되어야 함
                                            (BTS면 BTS 출력, 다른공연이면 다른공연 출력) *****************************적용 완료
        오류사항 :
        수정사항 : 2019-09-14-23시41분) : Ticketing_Activity에서 공연을 선택한 뒤, 그 공연에 맞는 공연 정보가 출력 되도록 수정완료
*/


public class Ticket_Activation_Activity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket__activation_);
        // BTS 공연을 선택했을 경우
        if (Concert_Information == 1) {
            ImageView IMG_BTS = (ImageView) findViewById(R.id.IMG_BTS);
            TextView BTS_text = (TextView) findViewById(R.id.BTS_text);
            TextView BTS_hint = (TextView) findViewById(R.id.BTS_hint);

            IMG_BTS.setVisibility(View.VISIBLE);
            BTS_text.setVisibility(View.VISIBLE);
            BTS_hint.setVisibility(View.VISIBLE);

            // 와 이 시발 이새끼 onCreate 위에선언했다고 존나 시발 에러개떴네 개새끼
            Button Reservation = (Button) findViewById(R.id.Reservation);
            Reservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "예매가 완료되었습니다", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

        }
        // 김경호 공연을 선택했을 경우
        else if (Concert_Information == 2) {

            ImageView IMG_kkh = (ImageView) findViewById(R.id.IMG_kkh);
            TextView kkh_text = (TextView) findViewById(R.id.kkh_text);
            TextView kkh_hint = (TextView) findViewById(R.id.kkh_hint);

            IMG_kkh.setVisibility(View.VISIBLE);
            kkh_text.setVisibility(View.VISIBLE);
            kkh_hint.setVisibility(View.VISIBLE);

            // 와 이 시발 이새끼 onCreate 위에선언했다고 존나 시발 에러개떴네 개새끼
            final Button Reservation = (Button) findViewById(R.id.Reservation);

            Reservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "예매가 완료되었습니다", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        // 서강준 팬미팅을 선택했을 경우
        else if (Concert_Information == 3) {
            ImageView IMG_skj = (ImageView) findViewById(R.id.IMG_skj);
            TextView skj_text = (TextView) findViewById(R.id.skj_text);
            TextView skj_hint = (TextView) findViewById(R.id.skj_hint);

            IMG_skj.setVisibility(View.VISIBLE);
            skj_text.setVisibility(View.VISIBLE);
            skj_hint.setVisibility(View.VISIBLE);
            // 와 이 시발 이새끼 onCreate 위에선언했다고 존나 시발 에러개떴네 개새끼
            final Button Reservation = (Button) findViewById(R.id.Reservation);

            Reservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "예매가 완료되었습니다", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        // 맘마미아 뮤지컬을 선택했을 경우
        else if (Concert_Information == 4) {
            ImageView IMG_Mammamia = (ImageView) findViewById(R.id.IMG_Mammamia);
            TextView Mammamia_text = (TextView) findViewById(R.id.Mammamia_text);
            TextView Mammamia_hint = (TextView) findViewById(R.id.Mammamia_hint);

            IMG_Mammamia.setVisibility(View.VISIBLE);
            Mammamia_text.setVisibility(View.VISIBLE);
            Mammamia_hint.setVisibility(View.VISIBLE);
            // 와 이 시발 이새끼 onCreate 위에선언했다고 존나 시발 에러개떴네 개새끼
            final Button Reservation = (Button) findViewById(R.id.Reservation);

            Reservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "예매가 완료되었습니다", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}

