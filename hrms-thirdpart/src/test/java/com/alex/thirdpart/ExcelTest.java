package com.alex.thirdpart;

import com.alex.thirdpart.service.ExcelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author _Alexzinv_
 * @Date 2021/10/5
 * @Description
 */
@SpringBootTest
public class ExcelTest {

    @Autowired
    private ExcelService excelService;

    @Test
    public void uploadTest() throws IOException {
        String filePath = "";
        FileInputStream inputStream = new FileInputStream(filePath);
        MockMultipartFile file = new MockMultipartFile("test", inputStream);
        // excelService.uploadExcel(file,);
    }

    @Test
    public void downloadTest(){


    }
}
