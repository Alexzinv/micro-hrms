package com.alex.member.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 自增序列生成
 */

public class SerialGenerator {

    /**
     * 初始数值
     */
    private static final AtomicLong AL = new AtomicLong(10001);

    public static Long initSerial() {
        /// 自定义开始和步长
        return AL.get();
    }
}
