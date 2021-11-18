package com.alex.thirdpart.controller;

import com.alex.common.util.R;
import com.alex.thirdpart.service.ExcelService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/24
 * @Description 通用的适配基于mybatis-plus服务的easyExcel服务
 */
@RestController
@RequestMapping("/third-part/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public R uploadExcel(MultipartFile file, T entity, IService<T> service){
        excelService.uploadExcel(file, entity, service);
        return R.ok();
    }

    @PostMapping("/download")
    public R downloadExcel(HttpServletResponse response, List<Long> ids,
                           T entity, IService<T> service){

        excelService.downloadExcel(response, ids, entity, service);
        return R.ok();
    }
}
