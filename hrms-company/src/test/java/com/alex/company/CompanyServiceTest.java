package com.alex.company;

import com.alex.company.entity.Company;
import com.alex.company.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/9
 * @Description
 */
@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Test
    public void saveTest(){
        Company company = new Company();
        company.setName("南斗导航科技有限公司");
        company.setManagerId(206463060410373L);
        boolean isSave = companyService.save(company);
        assert isSave;
    }

    @Test
    public void updateTest(){
        Company company = new Company();
        company.setId(225977538736133L);
        company.setLegalRepresentative("_ALEXZINV_");
        boolean isUpdate = companyService.updateById(company);
        assert isUpdate;
    }
}
