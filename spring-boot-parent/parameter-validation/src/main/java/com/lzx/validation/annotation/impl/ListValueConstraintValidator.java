package com.lzx.validation.annotation.impl;

import com.lzx.validation.annotation.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 创建约束校验器
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> set = new HashSet<>();

    /**
     * 初始化方法
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vale = constraintAnnotation.vals();
        for (int val : vale) {
            set.add(val);
        }
    }

    /**
     * 判断是否校验成功
     *
     * @param value 需要校验的值
     * @param context 校验上下文
     * @return 是否校验成功
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }

}
