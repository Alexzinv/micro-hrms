package com.alex.member.service;

import com.alex.common.config.CustomIdGenerator;
import com.alex.member.entity.UserCompany;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author _Alexzinv_
 * @date 2021/12/3
 * @description
 */
@SpringBootTest
public class UserCompanyServiceTest {

    @Autowired
    private UserCompanyService userCompanyService;

    @Autowired
    private CustomIdGenerator customIdGenerator;

    @Test
    public void saveTest(){
        UserCompany company = new UserCompany();
        Long id = customIdGenerator.nextId(company);
        company.setId(id);
        userCompanyService.save(company);
        System.out.println("--------------------------");
        UserCompany byId = userCompanyService.getById(id);
        System.out.println(byId);
    }
}
