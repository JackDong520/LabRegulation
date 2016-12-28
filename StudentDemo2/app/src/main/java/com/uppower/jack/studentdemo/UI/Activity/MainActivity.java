package com.uppower.jack.studentdemo.UI.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uppower.jack.studentdemo.R;
import com.uppower.jack.studentdemo.Utils.UserLogUp;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private UserLogUp userLogUp;
    private EditText editTextUserName;
    private EditText editTextUserPassword;
    public static final int RESPONSE_INFO_LOG_TP = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextUserName = (EditText) findViewById(R.id.edit_text_user_name);
        editTextUserPassword = (EditText) findViewById(R.id.edit_text_user_password);


        findViewById(R.id.log_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogUp = new UserLogUp(editTextUserName.getText().toString(),
                        editTextUserPassword.getText().toString(),
                        handler
                        );
                userLogUp.GetInfoFromService();
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
