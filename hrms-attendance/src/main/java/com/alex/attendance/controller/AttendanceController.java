package com.alex.attendance.controller;


import com.alex.attendance.entity.Attendance;
import com.alex.attendance.service.AttendanceService;
import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 出勤 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/attendance/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{attendId}")
    public R getAttendance(@PathVariable("attendId") Long attendId) {
        Attendance attendance = attendanceService.getById(attendId);
        return R.ok().data("data", attendance);
    }

    @GetMapping("/{userId}")
    public R listAttendanceByUserId(@PathVariable("userId") Long userId) {
        List<Attendance> attendances = attendanceService.listByUserId(userId);
        return R.ok().data("records", attendances);
    }

    @PostMapping("/save")
    public R saveAttendance(@Validated({AddGroup.class}) @RequestBody Attendance attendance) {
        attendanceService.save(attendance);
        return R.ok();
    }

    @GetMapping
    public R listAttendances() {
        List<Attendance> attendances = attendanceService.list();
        return R.ok().data("records", attendances);
    }

    @PutMapping
    public R updateAttendant(@RequestBody Attendance attendance){
        attendanceService.updateById(attendance);
        return R.ok();
    }

    @DeleteMapping
    public R deleteBatch(@RequestBody List<Long> ids){
        attendanceService.removeByIds(ids);
        return R.ok();
    }

}

