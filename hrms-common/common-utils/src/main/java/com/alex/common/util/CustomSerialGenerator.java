package com.alex.common.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 自增序列生成
 */

public class CustomSerialGenerator {

    private static final AtomicLong AL = new AtomicLong(10000);
    private static final AtomicLong CODE = new AtomicLong(1);

    /**
     * 工号初始数值
     */
    public static Long initSerial() {
        return AL.get();
    }

    /**
     * 编码专用
     */
    public static Long initCode() {
        return CODE.get();
    }
}
