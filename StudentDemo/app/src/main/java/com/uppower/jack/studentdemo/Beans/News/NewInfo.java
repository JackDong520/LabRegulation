package com.uppower.jack.studentdemo.Beans.News;

import java.io.Serializable;

/**
 * Created by 72408 on 2016/12/30.
 */

public class NewInfo implements Serializable {
    private String reason;

    private Result result;

    private int error_code;

    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
    public void setResult(Result result){
        this.result = result;
    }
    public Result getResult(){
        return this.result;
    }
    public void setError_code(int error_code){
        this.error_code = error_code;
    }
    public int getError_code(){
        return this.error_code;
    }

    @Override
    public String toString() {
        return "NewInfo{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
