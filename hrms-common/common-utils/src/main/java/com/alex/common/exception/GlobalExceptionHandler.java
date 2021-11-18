package com.alex.common.exception;



import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/9
 * @Description 异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 全局参数校验
	 * @param e 参数
	 * @return 异常信息
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public R handleValidException(MethodArgumentNotValidException e){
		log.error("数据校验出现问题{}, 异常类型{}", e.getMessage(), e.getStackTrace());
		BindingResult bindingResult = e.getBindingResult();
		Map<String, String> map = new HashMap<>(16);
	    bindingResult.getFieldErrors()
	            .forEach(r -> {
	                String msg = r.getDefaultMessage();
	                String field = r.getField();
	                map.put(field, msg);
	            });
		return R.err().code(ResultCodeEnum.INVALID_EXCEPTION.getCode())
				.message(ResultCodeEnum.INVALID_EXCEPTION.getMessage()).data("data", map);
	}

	/**
	 * 自定义异常
	 * @param e 异常类型
	 * @return 异常信息
	 */
	@ExceptionHandler(HRMSException.class)
	public R hrmsException(HRMSException e){
		log.error("自定义异常 -> ", e);
		if(e.getResultCodeEnum() != null){
			return R.err().code(e.getResultCodeEnum().getCode())
					.message(e.getResultCodeEnum().getMessage());
		}
		return R.err().code(e.getCode()).message(e.getMessage());
	}

	/**
	 * 全局异常处理
	 * @param e 异常类型
	 * @return 异常信息
	 */
	@ExceptionHandler(Exception.class)
	public R globalException(Exception e){
		log.error("全局异常 -> ", e);
		return R.err().code(ResultCodeEnum.UNKNOWN_EXCEPTION.getCode())
				.message(ResultCodeEnum.UNKNOWN_EXCEPTION.getMessage());
	}
}
