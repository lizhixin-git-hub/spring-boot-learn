package com.lzx.easy.poi.po;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;

/**
 * 用户信息
 */
public class User implements Serializable {

    // 数字格式化
    private NumberFormat nf = NumberFormat.getNumberInstance();

    private static final long serialVersionUID = 1L;

    @Excel(name = "用户id", orderNum = "0", width = 15)
    private long userId;

    @Excel(name = "性别", orderNum = "1", width = 15, replace = { "男_1", "女_2" }, suffix = "孩")
    private int sex;

    @Excel(name = "金钱", orderNum = "2", width = 15)
    private double money;

    @Excel(name = "用户信息", orderNum = "3", width = 15)
    private String userName;

    @Excel(name = "价格", orderNum = "4", width = 15)
    private float price;

    @Excel(name = "时间", orderNum = "5", width = 15, format = "yyyy-MM-dd")
    private Date now;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMoney() {
        return nf.format(money);
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }
}
