package com.lzx.sharding.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_udict")
public class Udict {

    @TableId("dictid")
    private Long dictid;

    @TableField("ustatus")
    private String ustatus;

    @TableField("uvalue")
    private String uvalue;

    public Long getDictid() {
        return dictid;
    }

    public void setDictid(Long dictid) {
        this.dictid = dictid;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUvalue() {
        return uvalue;
    }

    public void setUvalue(String uvalue) {
        this.uvalue = uvalue;
    }

    @Override
    public String toString() {
        return "Udict{" +
                "dictid=" + dictid +
                ", ustatus='" + ustatus + '\'' +
                ", uvalue='" + uvalue + '\'' +
                '}';
    }
}
