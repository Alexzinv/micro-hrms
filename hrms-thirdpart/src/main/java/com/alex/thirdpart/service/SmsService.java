package com.alex.thirdpart.service;

import java.util.HashMap;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 短信服务
 */
public interface SmsService {

	/**
	 * 发送验证码
	 * @param map 生成的验证码
	 * @param phone 手机号
	 * @return 是否成功发送
	 */
	boolean send(HashMap<String, Object> map, String phone);
}
