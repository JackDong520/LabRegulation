package com.uppower.jack.studentdemo.Beans.News;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 72408 on 2016/12/30.
 */

public class Result implements Serializable {
    private String stat;

    private List<Data> data ;

    public void setStat(String stat){
        this.stat = stat;
    }
    public String getStat(){
        return this.stat;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
}
