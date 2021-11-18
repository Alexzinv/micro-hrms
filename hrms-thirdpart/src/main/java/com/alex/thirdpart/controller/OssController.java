package com.alex.thirdpart.controller;


import com.alex.common.util.R;
import com.alex.thirdpart.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 阿里云OSS
 */
@RestController
@RequestMapping("/third-part/oss")
public class OssController {

	@Autowired
	private OssService ossService;

	@RequestMapping("/policy")
	public R policy(){
		Map<String, String> respMap = ossService.use();
		return R.ok().data("data", respMap);
	}
}
