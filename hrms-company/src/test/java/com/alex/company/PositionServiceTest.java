package com.alex.company;

import com.alex.company.entity.Position;
import com.alex.company.service.PositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author _Alexzinv_
 * @date 2021/12/21
 * @description position测试
 */
@SpringBootTest
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Test
    public void positionSaveTest(){
        Position position = new Position();
        positionService.save(position);
    }

    @Test
    public void positionUpdateRPCTest(){
        Position position = new Position();
        position.setName("aaa");
        boolean b = positionService.updateById(position);
        assert b;
    }
}
