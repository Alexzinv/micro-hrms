package com.alex.thirdpart.service.impl;

import cn.hutool.json.JSONObject;
import com.alex.thirdpart.config.SmsConfig;
import com.alex.thirdpart.service.SmsService;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 短信服务API
 */
@Service
public class SmsServiceImpl implements SmsService {
	@Override
	public boolean send(HashMap<String, Object> map, String phone) {
		if(StringUtils.hasText(phone)){
			DefaultProfile profile = DefaultProfile.getProfile("default", SmsConfig.KEY_ID, SmsConfig.KEY_SECRET);
			IAcsClient client = new DefaultAcsClient(profile);

			// 设置参数
			CommonRequest request = new CommonRequest();
			request.setSysMethod(MethodType.POST);
			request.setSysDomain("dysmsapi.aliyuncs.com");
			request.setSysVersion("2021-09-16");
			request.setSysAction("SendSms");

			request.putQueryParameter("PhoneNumbers", phone);
			request.putQueryParameter("SignName", "人力资源");
			request.putQueryParameter("TemplateCode", "SMS_218159097");
			request.putQueryParameter("TemplateParam", new JSONObject(map).toJSONString(0));

			try {
				CommonResponse response = client.getCommonResponse(request);
				return response.getHttpResponse().isSuccess();
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
