package com.lzx.validation.entity;

import com.lzx.validation.annotation.ListValue;
import com.lzx.validation.groups.Groups;

import javax.validation.constraints.*;

public class User {

    @Null(message = "新增不需要指定id", groups = Groups.Add.class)
    @NotNull(message = "修改需要指定id", groups = Groups.Update.class)
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @NotNull
    private String username;

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码必须为8~16个字母和数字组合")
    private String password;

    @Email
    private String email;

    @ListValue( message = "性别应指定相应的值" , vals = {1,2} , groups = {Groups.Add.class , Groups.Update.class})
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

}
