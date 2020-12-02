package com.lzx.excel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lzx.excel.annotation.Excel;

public class TbUser extends BaseEntity<TbUser> {

    @TableId(value = "id", type = IdType.AUTO)
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Integer id;

    @TableField("user_name")
    @Excel(name = "用户姓名")
    private String userName;

    @TableField("sex")
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private Byte sex;

    @TableField("age")
    @Excel(name = "用户年龄", cellType = Excel.ColumnType.NUMERIC, prompt = "用户年龄")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
