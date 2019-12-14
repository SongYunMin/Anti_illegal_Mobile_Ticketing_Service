package com.example.moblic_ticketing_service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
        작성자 : 송윤민
        최초작성일 : ??
        실행환경 : Android Studio 3.4
        작성목적 : PHP와 통신하는 스레드
        필요사항 : Thread의 정확한 이해
        오류사항 :
        수정사항 :
*/

public class HttpConnectThread extends Thread {
    private String m_Url;
    private String mContent;


    public HttpConnectThread(String url, String Content) {
        m_Url = url;
        mContent = Content;
    }

    String temp = "";

    // request를 실행하다가 에러가 나면 에러 메세지를 temp안에 담는 함수
    public void run() {
        try {
            temp = request(); // 이새끼 아래에  있음
        } catch (Exception e) {
            temp = e.getMessage();
        }
    }

    public String GetResult() {
        return temp;
    }

    private String request() {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(m_Url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn == null) {
                return "";
            }

            conn.setConnectTimeout(5000);
            conn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
            conn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
            conn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream out = conn.getOutputStream();
            out.write(mContent.getBytes());

            int resCode = conn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String line = null;
                while (true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    output.append(line + "\n");
                }
                reader.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            return e + "";
        }
        return output.toString();
    }
}