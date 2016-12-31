package com.uppower.jack.studentdemo.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uppower.jack.studentdemo.Beans.News.Data;
import com.uppower.jack.studentdemo.Beans.News.NewInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 72408 on 2016/12/30.
 */

public class DataFromApiUtil {



    private OkHttpClient client = new OkHttpClient();

    private String url ;

    private Bitmap mBitmap;


    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getImage(){

        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            InputStream is = response.body().byteStream();
            mBitmap = BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mBitmap;
    }


}
