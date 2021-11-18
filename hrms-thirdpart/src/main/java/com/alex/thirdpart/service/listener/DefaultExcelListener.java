package com.alex.thirdpart.service.listener;


import com.alex.common.exception.HRMSException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author _Alexzinv_
 * @Date 2021/10/2
 * @Description excel操作
 * 该类不能交给spring管理
 */
@Slf4j
public class DefaultExcelListener<T> extends AnalysisEventListener<T> {

	/**
	 * 每批数量大小
	 */
	private static final int BATCH_SIZE = 1000;
	/**
	 * 数据行数
	 */
	private final List<T> rows = new ArrayList<>();
	/**
	 * mybatis-plus service父接口
	 */
	private final IService<T> service;

	public DefaultExcelListener(IService<T> service){
		this.service = service;
	}

	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
	}

	@Override
	public void invoke(T object, AnalysisContext context) {
		if(Objects.isNull(object)){
			throw new HRMSException(20001, "数据为空");
		}

		rows.add(object);
		// 数据分批存储，并清空列表，以防止内存占用过多造成OOM
		if(BATCH_SIZE <= rows.size()){
			service.saveBatch(rows);
			rows.clear();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		// 解析后不为空需要保存
		if (CollectionUtils.isEmpty(rows)) {
			return;
		}
		log.info("read {} rows", rows.size());
		service.saveBatch(rows);
		rows.clear();
	}

	/**
	 * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
	 * @param exception 异常类型
	 * @param context 内容
	 */
	@Override
	public void onException(Exception exception, AnalysisContext context) {
		log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
		if (exception instanceof ExcelDataConvertException) {
			ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
			log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
					excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
		}
	}

	public List<T> getRows() {
		return rows;
	}
}
