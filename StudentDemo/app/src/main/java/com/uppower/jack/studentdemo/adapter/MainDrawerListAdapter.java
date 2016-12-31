package com.uppower.jack.studentdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uppower.jack.studentdemo.Beans.News.Data;
import com.uppower.jack.studentdemo.R;

import java.util.List;

/**
 * Created by 72408 on 2016/12/30.
 */

public class MainDrawerListAdapter extends RecyclerView.Adapter<MyViewholder>  {

    private LayoutInflater mInflater;
    private Context context;
    private List<Data>datas;

    public interface OnItemClickListener{
        void onItemClick(View view , int position ,List<Data>datas);
        void onItemLongClick(View view ,int position,List<Data>datas);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }


    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public MainDrawerListAdapter(Context context, List<Data>datas) {
        this.context = context;
        this.datas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    //创建V
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_news , parent , false);
        MyViewholder viewholder = new MyViewholder(view);


        return viewholder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(final MyViewholder holder, final int position) {
        holder.date_tv.setText(datas.get(position).getDate());
        holder.title_tv.setText(datas.get(position).getTitle());
        holder.writer_tv.setText(datas.get(position).getAuthor_name());

        if(mOnItemClickListener!=null){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("在Adapter中调用");
                    mOnItemClickListener.onItemClick(holder.itemView , position ,datas);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(holder.itemView , position ,datas);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //返回数据总数


}


class MyViewholder extends RecyclerView.ViewHolder
{

    TextView writer_tv;
    TextView title_tv;
    TextView date_tv;
    public MyViewholder(View itemView) {
        super(itemView);
        writer_tv = (TextView) itemView.findViewById(R.id.new_writer);
        title_tv = (TextView) itemView.findViewById(R.id.new_title);
        date_tv = (TextView) itemView.findViewById(R.id.new_date);
    }

}
