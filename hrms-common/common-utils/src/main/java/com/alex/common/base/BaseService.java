package com.alex.common.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author _Alexzinv_
 * @date 2022/1/3
 * @description 条件查询
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 分页条件查询
     * @param page 当前页
     * @param limit 每页限制
     * @param query 查询条件
     * @return 查询结果
     */
    default Page<T> listPage(Integer page, Integer limit, BaseQuery query){
         Page<T> pageEntity = new Page<>(page, limit);
         LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
         buildCondition(wrapper, query);
         return page(pageEntity, wrapper);
    }

    /**
     * 构造查询条件wrapper
     * @param wrapper 条件对象
     * @param query 查询条件
     */
    void buildCondition(LambdaQueryWrapper<T> wrapper, BaseQuery query);
}
