package com.alex.thirdpart.service.impl;

import com.alex.thirdpart.service.ExcelService;
import com.alex.thirdpart.service.listener.DefaultExcelListener;
import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/24
 * @Description
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public void uploadExcel(MultipartFile file, T entity, IService<T> service) {
        try {
            InputStream is = file.getInputStream();
            EasyExcelFactory.read(is, entity.getClass(), new DefaultExcelListener<>(service))
                    .sheet().doRead();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void downloadExcel(HttpServletResponse response, List<Long> ids, T entity, IService<T> service) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            // TODO 按当前业务取文件名
            fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        List<T> list = service.listByIds(ids);
        try {
            EasyExcelFactory.write(response.getOutputStream(), entity.getClass()).sheet("1").doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
