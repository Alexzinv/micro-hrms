package com.alex.common.util;

import com.alex.common.consant.ResultCodeEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/8
 * @Description 统一返回类
 */
@Data
public class R {

	/**
	 * 是否成功
	 */
	private Boolean success;

	/**
	 * 返回码
	 */
	private Integer code;

	/**
	 * 返回消息
	 */
	private String message;

	/**
	 * 返回数据
	 */
	private Map<String, Object> data;

	/// 懒汉
	private static R r = new R();


	private R(){}

	/**
	 * 单例
	 * @return r
	 */
	private static R getR(){
		return r;
	}

	/**
	 * 成功静态方法
	 * @return R
	 */
	public static R ok(){
		R r = getR();
		r.setSuccess(true);
		r.setCode(ResultCodeEnum.SUCCESS.getCode());
		r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
		r.setData(new HashMap<>(16));
		return r;
	}

	/**
	 * 失败静态方法
	 * @return R
	 */
	public static R err(){
		R r = getR();
		r.setSuccess(false);
		r.setCode(ResultCodeEnum.ERROR.getCode());
		r.setMessage(ResultCodeEnum.ERROR.getMessage());
		r.setData(new HashMap<>(16));
		return r;
	}

	public R result(ResultCodeEnum codeEnum){
		this.setCode(codeEnum.getCode());
		this.setMessage(codeEnum.getMessage());
		return this;
	}

	public R message(String message){
		this.setMessage(message);
		return this;
	}

	public R code(Integer code){
		this.setCode(code);
		return this;
	}

	public R data(String key, Object value){
		this.data.put(key, value);
		return this;
	}

	public R data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}

}
