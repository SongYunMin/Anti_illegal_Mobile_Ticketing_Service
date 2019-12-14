package com.example.moblic_ticketing_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : Ticketing_Activity 에서 컨텐츠(공연) 을 클릭했을때 실행되는 Activity
        필요사항 : 2019-09-14-02시52분) : 여러 컨텐츠가 있는데 동적으로 공연이 출력 되어야 함
                                            (BTS면 BTS 출력, 다른공연이면 다른공연 출력)
        오류사항 :
        수정사항 : ---------------------- 사용 보류 Activity -------------------------------
                    (비 효율적인 Activity, 쓸데없는 예약을 한번 더 누르게 됨.
*/


public class Ticket_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_);

        // 티켓이 여러게있으면 여러게의 onClick메서드를 만드는 것은 효율적이지 않음 switch 문 사용 권장
        Button bt_BTS = (Button) findViewById(R.id.BIS_Button_1);

        // BTS 이미지를 눌렀을때에 이벤트 처리
        bt_BTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ticket_Activation_Activity.class);
                startActivity(intent);
            }
        });
    }
}
