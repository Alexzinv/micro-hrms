package com.alex.attendance.controller;


import com.alex.attendance.entity.AttendanceConfig;
import com.alex.attendance.service.AttendanceConfigService;
import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 考勤配置表 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/attendance/attendanceConfig")
public class AttendanceConfigController {

    private final AttendanceConfigService attendanceConfigService;

    public AttendanceConfigController(AttendanceConfigService attendanceConfigService){
        this.attendanceConfigService = attendanceConfigService;
    }

    @GetMapping("/{configId}")
    public R getAttendanceConfig(@PathVariable("configId") Long configId){
        AttendanceConfig attendanceConfig = attendanceConfigService.getById(configId);
        return R.ok().data("data", attendanceConfig);
    }

    @GetMapping
    public R listAttendanceConfigs() {
        List<AttendanceConfig> list = attendanceConfigService.list();
        return R.ok().data("records", list);
    }

    @PostMapping("/save")
    public R saveAttendance(@Validated({AddGroup.class}) @RequestBody AttendanceConfig attendanceConfig) {
        attendanceConfigService.save(attendanceConfig);
        return R.ok();
    }

    @PutMapping("/update")
    public R updateAttendance(@RequestBody AttendanceConfig attendanceConfig) {
        attendanceConfigService.updateById(attendanceConfig);
        return R.ok();
    }

    @PutMapping("/delete")
    public R batchRemove(@RequestBody List<Long> ids) {
        attendanceConfigService.removeByIds(ids);
        return R.ok();
    }

}

