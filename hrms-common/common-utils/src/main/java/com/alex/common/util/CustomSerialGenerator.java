package com.alex.common.util;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 自增序列生成
 */

public class CustomSerialGenerator {

    private static final LongAccumulator CODE = new LongAccumulator(Long::sum, 1);
    private static final LongAccumulator WORK_NUM = new LongAccumulator(Long::sum, 10000);

    /**
     * 工号初始数值
     */
    public static Long initSerial() {
        return WORK_NUM.get();
    }

    /**
     * 编码专用
     */
    public static Long initCode() {
        return CODE.get();
    }
}
