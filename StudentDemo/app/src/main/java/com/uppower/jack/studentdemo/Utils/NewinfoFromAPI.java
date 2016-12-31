package com.uppower.jack.studentdemo.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uppower.jack.studentdemo.Beans.News.Data;
import com.uppower.jack.studentdemo.Beans.News.NewInfo;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 72408 on 2016/12/30.
 */

public class NewinfoFromAPI {

    private OkHttpClient client = new OkHttpClient();

    private HttpUrl url = HttpUrl.parse("http://v.juhe.cn/toutiao/index").newBuilder()
            .addQueryParameter("type","top")
            .addQueryParameter("key","02974f77c8e4ee181c7714ba6e949cfd")
            .build();

    private Request request = new Request.Builder().url(url).
            build();

    private List<Data> datas;

    public List<Data> GetListNewInfo(){
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String JsonString = response.body().string();
                System.out.println(JsonString);

                Gson gson = new GsonBuilder().create();


                NewInfo newInfo = gson.fromJson(JsonString, NewInfo.class);
                 datas = newInfo.getResult().getData();
                for (int i = 0 ; i <datas.size() ;i++){
                    System.out.println(datas.get(i).getTitle());
                }
                datas = newInfo.getResult().getData();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

}
