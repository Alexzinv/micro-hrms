package com.alex.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author _Alexzinv_
 * @date 2021/12/21
 * @description 数据校验
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {

    /**
     * 存储需要校验的值
     */
    private final Set<Integer> set = new HashSet<>();

    /**
     * 初始化方法
     * @param constraintAnnotation 校验值
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        for (int v : values) {
            set.add(v);
        }
    }

    /**
     *
     * @param integer 需要校验的值
     * @param constraintValidatorContext 校验上下文环境
     * @return 值是否正确
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}

