package com.alex.company.controller;


import com.alex.company.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-15
 */
@RestController
@RequestMapping("/company/position")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    PositionController(PositionService positionService){
        this.positionService = positionService;
    }



}

