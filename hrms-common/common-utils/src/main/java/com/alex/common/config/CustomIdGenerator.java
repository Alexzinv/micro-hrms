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
    /**
     * 生成器选项
     */
    private static final IdGeneratorOptions ID_GENERATOR_OPTIONS = new IdGeneratorOptions();

    @Override
    public Long nextId(Object entity) {
        // ID_GENERATOR_OPTIONS.SeqBitLength = 6;
        // ID_GENERATOR_OPTIONS.MinSeqNumber = 5;
        YitIdHelper.setIdGenerator(ID_GENERATOR_OPTIONS);
        return YitIdHelper.nextId();
    }
}
