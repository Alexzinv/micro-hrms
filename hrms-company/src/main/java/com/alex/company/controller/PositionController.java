package com.alex.company.controller;


import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.QueryGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.alex.company.dto.PositionQuery;
import com.alex.company.dto.PositionStatusVO;
import com.alex.company.dto.struct.PositionStruct;
import com.alex.company.entity.Position;
import com.alex.company.service.PositionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author _Alexzinv_
 * @date 2021-12-15
 * @description 岗位controller
 */
@RestController
@RequestMapping("/company/position")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    PositionController(PositionService positionService){
        this.positionService = positionService;
    }

    @GetMapping("/get/{id}")
    public R getPosition(@PathVariable("id") Long id){
        Position position = positionService.getById(id);
        return R.ok().data("position", position);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listPositionCondition(@PathVariable Integer page,
                                   @PathVariable Integer limit,
                                   @Validated({QueryGroup.class})
                                   @RequestBody(required = true) PositionQuery positionQuery){
        System.out.println(positionQuery.toString());
        Page<Position> result = new Page<>();
        if(positionQuery.getCompanyId() != null){
            result = positionService.listPage(page, limit, positionQuery);
        }
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R savePosition(@Validated({AddGroup.class}) @RequestBody Position position){
        positionService.save(position);
        return R.ok();
    }

    @PostMapping("/update")
    public R updatePosition(@Validated({UpdateGroup.class}) @RequestBody Position position){
        positionService.updateById(position);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deletePosition(@PathVariable("id") Long id){
        positionService.removeById(id);
        return R.ok();
    }

    @PostMapping("/status")
    public R statusPosition(@Validated({UpdateStatusGroup.class}) @RequestBody PositionStatusVO vo){
        Position position = PositionStruct.INSTANCE.statusVoToEntity(vo);
        positionService.updateById(position);
        return R.ok();
    }

}

