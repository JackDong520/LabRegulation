package com.uppower.jack.studentdemo.Beans;

/**
 * Created by 72408 on 2016/12/30.
 */

public class StuInfo {
    private String stu_name;
    private String stu_password;
    private String stu_email;
    private String stu_sid;
    private String stu_moblie;

    public StuInfo(String stu_email, String stu_moblie, String stu_name, String stu_password, String stu_sid) {
        this.stu_email = stu_email;
        this.stu_moblie = stu_moblie;
        this.stu_name = stu_name;
        this.stu_password = stu_password;
        this.stu_sid = stu_sid;
    }

    public String getStu_email() {
        return stu_email;
    }

    public void setStu_email(String stu_email) {
        this.stu_email = stu_email;
    }

    public String getStu_moblie() {
        return stu_moblie;
    }

    public void setStu_moblie(String stu_moblie) {
        this.stu_moblie = stu_moblie;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_password() {
        return stu_password;
    }

    public void setStu_password(String stu_password) {
        this.stu_password = stu_password;
    }

    public String getStu_sid() {
        return stu_sid;
    }

    public void setStu_sid(String stu_sid) {
        this.stu_sid = stu_sid;
    }

    @Override
    public String toString() {
        return "StuInfo{" +
                "stu_email='" + stu_email + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", stu_password='" + stu_password + '\'' +
                ", stu_sid='" + stu_sid + '\'' +
                ", stu_moblie='" + stu_moblie + '\'' +
                '}';
    }
}
