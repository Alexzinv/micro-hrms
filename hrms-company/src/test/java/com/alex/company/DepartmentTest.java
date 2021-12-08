package com.alex.company;


import com.alex.company.entity.Department;
import com.alex.company.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/9
 * @Description
 */
@SpringBootTest
public class DepartmentTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void test(){
        Department department = new Department();
        department.setCompanyId(225977538736133L);
        department.setName("CDM");
        departmentService.save(department);
    }

}
