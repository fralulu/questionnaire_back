package com.infore.common.reqVO;

/**
 * Created by xuyao on 2017/6/27.
 * 接收前端数据的对象
 */
public class TestInfoVO {
    private String userName;
    private String pwd;
    private String qq;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "TestInfoVO{" +
            "userName='" + userName + '\'' +
            ", pwd='" + pwd + '\'' +
            ", qq='" + qq + '\'' +
            '}';
    }
}
