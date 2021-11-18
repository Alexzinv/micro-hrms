package com.alex.thirdpart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/24
 * @Description Excel服务类
 */
public interface ExcelService {

    /**
     * 上传Excel
     * @param file excel文件
     * @param entity 需要操作的实体类
     * @param service mybatis-plus服务类
     */
    void uploadExcel(MultipartFile file, T entity, IService<T> service);

    /**
     * 导出Excel
     * @param response 响应
     * @param ids 要导出所属部分对象的id
     * @param entity 需要操作的实体类
     * @param service mybatis-plus服务类
     */
    void downloadExcel(HttpServletResponse response, List<Long> ids, T entity, IService<T> service);
}
