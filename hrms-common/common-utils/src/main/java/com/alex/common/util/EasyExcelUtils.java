package com.alex.common.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.handler.WriteHandler;
import org.apache.poi.ss.formula.functions.T;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author _Alexzinv_
 * @Date 2021/10/2
 * @Description excel工具类
 */
public class EasyExcelUtils {

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     * @param inputStream 文件流
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     * @return List<Map<colNum, cellValue>>
     */
    public static List<Map<Integer, String>> syncRead(InputStream inputStream, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步按模型读（指定sheet和表头占的行数）
     * @param inputStream 文件流
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     * @return List<Map<colNum, cellValue>>
     */
    public static List<T> syncReadModel(InputStream inputStream, Class clazz, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }


    /**
     * 异步无模型读（默认读取sheet0,从第2行开始读）
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param filePath 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener){
        EasyExcelFactory.read(filePath, excelListener).sheet().doRead();
    }

    /**
     * 异步无模型读（默认表头占一行，从第2行开始读）
     * @param filePath 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener, Integer sheetNo){
        EasyExcelFactory.read(filePath, excelListener).sheet(sheetNo).doRead();
    }

    /**
     * 异步无模型读（指定sheet和表头占的行数）
     * @param inputStream 文件流
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(InputStream inputStream, AnalysisEventListener<T> excelListener, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(inputStream, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步按模型读取
     * @param inputStream 文件流
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo  sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncReadModel(InputStream inputStream, AnalysisEventListener<T> excelListener, Class clazz, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(inputStream, clazz, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 无模板写文件
     * @param filePath 路径
     * @param head 表头数据
     * @param data 表内容数据
     */
    public static void write(String filePath, List<List<String>> head, List<List<Object>> data){
        EasyExcelFactory.write(filePath).head(head).sheet().doWrite(data);
    }

    /**
     * 无模板写文件
     * @param filePath 路径
     * @param head 表头数据
     * @param data 表内容数据
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, List<List<String>> head, List<List<Object>> data, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath).head(head).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 根据excel模板文件写入文件
     * @param filePath 文件路径
     * @param templateFileName 模板
     * @param headClazz 表头模板
     * @param data 数据
     */
    public static void writeTemplate(String filePath, String templateFileName, Class headClazz, List data){
        EasyExcelFactory.write(filePath, headClazz).withTemplate(templateFileName).sheet().doWrite(data);
    }

    /**
     * 根据excel模板文件写入文件
     * @param filePath 文件路径
     * @param templateFileName 模板
     * @param data 数据
     */
    public static void writeTemplate(String filePath, String templateFileName, List data){
        EasyExcelFactory.write(filePath).withTemplate(templateFileName).sheet().doWrite(data);
    }

    /**
     * 按模板写文件
     * @param filePath 文件路径
     * @param headClazz 表头模板
     * @param data 数据
     */
    public static void write(String filePath, Class headClazz, List data){
        EasyExcelFactory.write(filePath, headClazz).sheet().doWrite(data);
    }

    /**
     * 按模板写文件
     * @param filePath 文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, Class headClazz, List data, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件
     * @param filePath 文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param writeHandler 自定义的处理器，比如设置table样式，设置超链接、单元格下拉框等等功能都可以通过这个实现（需要注册多个则自己通过链式去调用）
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, Class headClazz, List data, WriteHandler writeHandler, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).registerWriteHandler(writeHandler).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件（包含某些字段）
     * @param filePath 文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param includeCols 过滤包含的字段，根据字段名称过滤
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void writeInclude(String filePath, Class headClazz, List data, Set<String> includeCols, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).includeColumnFiledNames(includeCols).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件（排除某些字段）
     * @param filePath 文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param excludeCols 过滤排除的字段，根据字段名称过滤
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void writeExclude(String filePath, Class headClazz, List data, Set<String> excludeCols, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).excludeColumnFiledNames(excludeCols).sheet(sheetNo, sheetName).doWrite(data);
    }
}
