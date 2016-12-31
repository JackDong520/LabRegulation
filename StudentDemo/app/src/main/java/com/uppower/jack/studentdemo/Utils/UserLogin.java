package com.uppower.jack.studentdemo.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 72408 on 2016/12/28.
 */

public class UserLogin {

        private String userName;
        private String userPassword;
        private OkHttpClient client;
        public static final int RESPONSE_INFO_LOG_TP = 101;


        private Handler handler;
        public UserLogin(String userName, String userPassword , Handler handler) {
            this.userName = userName;
            this.userPassword = userPassword;
            this.handler = handler;
        }

        public void GetInfoFromService()
        {
            new Thread(){
                @Override
                public void run() {
                    super.run();

                    client = new OkHttpClient();

                    HttpUrl httpUrl = HttpUrl.parse("http://192.168.1.120:8080/TestApp/LoginServlet")
                            .newBuilder()
                            .addQueryParameter("user_name",userName)
                            .addQueryParameter("user_password" ,userPassword)
                            .build();

                    System.out.println(httpUrl.toString());
                    Request request = new Request.Builder().url(httpUrl).build();

                    try {
                        Response response = client.newCall(request).execute();
                        if (response.isSuccessful())
                        {

                            String responseCode = response.body().string();


                            Bundle bundle = new Bundle();
                            bundle.putString("responseCode",responseCode);
                            Message message = new Message();
                            message.setData(bundle);
                            message.what = RESPONSE_INFO_LOG_TP;
                            handler.sendMessage(message);

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }

}


