package com.alex.common.util;

import com.alex.common.consant.ResultCodeEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/8
 * @Description 统一返回类
 */
@Getter
public class R implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 成功 */
	private static final int SUCCESS = 20000;
	/** 成功消息 */
	private static final String SUCCESS_MESSAGE = "操作成功";
	/** 失败 */
	private static final int ERROR = 20001;
	/** 失败消息 */
	private static final String ERROR_MESSAGE = "操作成功";
	/** 是否成功 */
	private boolean success;
	/** 返回码 */
	private Integer code;
	/** 返回消息 */
	private String message;
	/** 返回数据 */
	private Map<String, Object> data = new HashMap<>(8);

	private R(){}

	/**
	 * 成功静态方法
	 * @return R
	 */
	public static R ok(){
		R r = new R();
		r.success = true;
		r.code(SUCCESS);
		r.message(SUCCESS_MESSAGE);
		return r;
	}

	/**
	 * 失败静态方法
	 * @return R
	 */
	public static R err(){
		R r = new R();
		r.success = false;
		r.code(ERROR);
		r.message(ERROR_MESSAGE);
		return r;
	}

	public R result(ResultCodeEnum codeEnum){
		code(codeEnum.getCode());
		message(codeEnum.getMessage());
		return this;
	}

	public R message(String message){
		this.message = message;
		return this;
	}

	public R code(Integer code){
		this.code = code;
		return this;
	}

	public R data(String key, Object value){
		this.data.put(key, value);
		return this;
	}

	public R data(Map<String, Object> map) {
		if(this.data.isEmpty()){
			this.data = map;
			return this;
		}
		this.data.putAll(map);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				"\"success\":" + success +
				", \"code\":" + code +
				", \"message\":\"" + message + '\"' +
				", \"data\":" + data +
				'}';
	}
}
