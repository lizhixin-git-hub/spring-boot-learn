package com.lzx.excel.entity;

import java.io.Serializable;
import java.util.Date;

public class HutoolUser implements Serializable {

    private String name;

    private String age;

    private Date birthDay;

    public HutoolUser() {
    }

    public HutoolUser(String name, String age, Date birthDay) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

}
