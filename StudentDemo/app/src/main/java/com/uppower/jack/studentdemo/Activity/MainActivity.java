package com.uppower.jack.studentdemo.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.uppower.jack.studentdemo.Beans.News.Data;
import com.uppower.jack.studentdemo.R;
import com.uppower.jack.studentdemo.Utils.NewinfoFromAPI;
import com.uppower.jack.studentdemo.adapter.DrawerLIstAdapter;
import com.uppower.jack.studentdemo.adapter.MainDrawerListAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 72408 on 2016/12/30.
 */

public class MainActivity extends AppCompatActivity {

    private ListView mListView ;
    private DrawerLIstAdapter mDawerLIstAdapter ;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;//监听Drawer的打开或则关闭
    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;

    private List<Data>mDatas;

    private MainDrawerListAdapter mAdapter;

    private String[] mPlanetTitles={"0", "1" ,"2"};

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 100:
                    Bundle bundle = msg.getData();
                    List<Data>datas = (List<Data>) bundle.get("mDatas");
                    mAdapter.setDatas(datas);
                    mRecyclerView.setAdapter(mAdapter);
                    //设置RecyclerView布局管理
                    LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL ,false);
                    mRecyclerView.setLayoutManager(lm);
                    //设置RecyclerView的Item间的分割线
                    mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this , DividerItemDecoration.VERTICAL));

                    break;
            }
        }
    };




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.item_drawer_list_view,mPlanetTitles));

        mListView.setOnItemClickListener(new drawerOnItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.mipmap.drawer,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(getApplicationContext() , "抽屉关闭" , Toast.LENGTH_SHORT);
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext() , "抽屉打开" , Toast.LENGTH_SHORT);

            }
        };
        //设置抽屉打开与关闭的事件


        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //-----------------------------------------------


        mAdapter = new MainDrawerListAdapter(getApplicationContext() ,mDatas);

        mAdapter.setmOnItemClickListener(new MainDrawerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position ,List<Data>datas) {
                Intent intent = new Intent(MainActivity.this  ,NewsInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data" , datas.get(position));
                intent.putExtra("data",bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position , List<Data>datas) {
                Toast.makeText(getApplicationContext() , "longclick"+position,
                        Toast.LENGTH_SHORT).show();

            }
        });//设置Item的点击事件

        //-------------------------------------------------------
        //设置初始化Rycylerview中的数据
        initDatas();

    }


    //配置Drawerlayout中的ListView的监听器
    public class drawerOnItemClickListener  implements ListView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }



    private void initViews() {

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView_drawerlayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mListView = (ListView) findViewById(R.id.List_item_left);

        mToolbar = (Toolbar) findViewById(R.id.Toolbar);
        mToolbar.setTitle("UpPwer");

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView_drawerlayout);
    }

    private void initDatas() {



        new Thread() {
            @Override
            public void run() {
                super.run();
                NewinfoFromAPI newinfoFromAPI = new NewinfoFromAPI();
                mDatas = newinfoFromAPI.GetListNewInfo();



                Bundle bundle = new Bundle();
                bundle.putSerializable("mDatas" , (Serializable) mDatas);
                Message msg = new Message();
                msg.setData(bundle);
                msg.what = 100;
                handler.sendMessage(msg);

            }
        }.start();
    }

}
