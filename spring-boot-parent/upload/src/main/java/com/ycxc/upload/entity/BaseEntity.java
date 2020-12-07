package com.ycxc.upload.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 主类
 **/
public abstract class BaseEntity<T extends Model> extends Model<T> implements Serializable {

}
