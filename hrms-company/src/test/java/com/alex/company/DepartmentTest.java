package com.alex.company;


import com.alex.company.entity.Department;
import com.alex.company.service.DepartmentService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    public void saveTest(){
        Department department = new Department();
        department.setCompanyId(225977538736133L);
        department.setName("AIS");
        departmentService.save(department);
    }

    @Test
    public void listByCompanyIdTest(){
        Long companyId = 225977538736133L;
        List<Department> list = departmentService.list(
                Wrappers.lambdaQuery(Department.class)
                        .eq(Department::getCompanyId, companyId));
        assert CollectionUtils.isNotEmpty(list);
    }

}
