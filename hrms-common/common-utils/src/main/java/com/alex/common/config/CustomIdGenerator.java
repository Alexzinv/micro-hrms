package com.alex.common.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Component;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/25
 * @Description 自定义id生成 雪花漂移算法
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    /// 自定义生成id初始数
    // private final AtomicLong al = new AtomicLong(100001);

    @Override
    public Long nextId(Object entity) {
        // String bizKey = entity.getClass().getName();

        /// 自定义开始和步长
        // MetaObject metaObject = SystemMetaObject.forObject(entity);
        // final long id = al.getAndAdd(1);
        // return id;

        // 雪花漂移生成id
        IdGeneratorOptions options = new IdGeneratorOptions();
        // options.WorkerIdBitLength = 10;
        YitIdHelper.setIdGenerator(options);


        return YitIdHelper.nextId();
    }
}
