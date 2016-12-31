package com.uppower.jack.studentdemo.UI.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.uppower.jack.studentdemo.R;
import com.uppower.jack.studentdemo.Utils.UserLogUp;
import com.uppower.jack.studentdemo.Utils.UserLogin;

/**
 * Created by 72408 on 2016/12/28.
 */

public class LoginActivity extends Activity {
    private Handler handler;

    private UserLogin userLogin;
    private EditText editTextUserName;
    private EditText editTextUserPassword;
    public static final int RESPONSE_INFO_LOG_TP = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        editTextUserName = (EditText) findViewById(R.id.edit_text_user_name_login);
        editTextUserPassword = (EditText) findViewById(R.id.edit_text_user_password_login);


        findViewById(R.id.log_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin = new UserLogin(editTextUserName.getText().toString(),
                        editTextUserPassword.getText().toString(),
                        handler
                );
                userLogin.GetInfoFromService();
            }
        });


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case RESPONSE_INFO_LOG_TP:
                        Bundle bundle = msg.getData();
                        String responseString = bundle.getString("responseCode");
                        if (responseString.equals("200")){
                            Toast.makeText(getApplicationContext(), "注册成功",Toast.LENGTH_SHORT).show();
                        }
                        if (responseString.equals("404")){
                            Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT ).show();
                        }
                }
            }
        };





    }






}
