package com.uppower.jack.studentdemo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.uppower.jack.studentdemo.Beans.News.Data;
import com.uppower.jack.studentdemo.R;
import com.uppower.jack.studentdemo.Utils.DataFromApiUtil;

/**
 * Created by 72408 on 2016/12/30.
 */

public class NewsInfoActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView dateText;
    private TextView writerText;
    private ImageView firstImage;
    private ImageView secondImage;
    private Data data ;//从上个Activity传来的数据

    DataFromApiUtil util ;//用于从Api接受数据的工具


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newinfo);

        titleText = (TextView) findViewById(R.id.new_title);
        dateText = (TextView) findViewById(R.id.new_date);
        writerText = (TextView) findViewById(R.id.new_writer);
        firstImage = (ImageView) findViewById(R.id.image_first);

        initViews();
        loadImage();


    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    Bundle bundle = msg.getData();
                    Bitmap image = (Bitmap) bundle.get("image");
                    firstImage.setImageBitmap(image);
                    break;//加载第一张图片
            }
        }
    };

    private void loadImage() {

        util = new DataFromApiUtil();
        util.setUrl(data.getThumbnail_pic_s());

        new Thread(){
            @Override
            public void run() {
                super.run();
                Bitmap image = util.getImage();
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelable("image",image);
                msg.setData(bundle);
                msg.what = 101;
                handler.sendMessage(msg);
            }
        }.start();


    }

    private void initViews() {

        Intent intent = getIntent();

        data = (Data) intent.getBundleExtra("data").getSerializable("data");
        titleText.setText(data.getTitle());
        dateText.setText(data.getDate());
        writerText.setText(data.getAuthor_name());
    }
}
