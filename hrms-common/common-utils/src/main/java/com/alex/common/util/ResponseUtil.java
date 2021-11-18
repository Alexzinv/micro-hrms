package com.alex.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author _
 * @Date 2021/9/22
 * @Description 响应工具类
 */

public class ResponseUtil {
	public static void out(HttpServletResponse response, R r) {
		ObjectMapper mapper = new ObjectMapper();
		response.setStatus(HttpStatus.OK.value());

		response.setContentType("application/json;charset=UTF-8");
		try {
			mapper.writeValue(response.getOutputStream(), r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
