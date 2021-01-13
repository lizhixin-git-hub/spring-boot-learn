package com.lzx.validation.groups;

/**
 * 分组解决校验
 *
 * 新增和修改对于实体的校验规则是不同的，例如id是自增的时候，新增时id要为空，修改则必须不为空；新增和修改，若用的恰好又是同一种实体，那就需要用到分组校验。
 *
 * 校验注解都有一个groups属性，可以将校验注解分组
 */
public class Groups {

    public interface Add{}

    public interface  Update{}

}
