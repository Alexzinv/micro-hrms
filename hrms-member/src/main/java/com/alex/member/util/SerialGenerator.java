package com.alex.member.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 工号序列生成
 */

public class SerialGenerator {

    private static final AtomicLong AL = new AtomicLong(10000001);

    public static Long getSerial() {
        /// 自定义开始和步长
        return AL.getAndAdd(1);
    }
}
