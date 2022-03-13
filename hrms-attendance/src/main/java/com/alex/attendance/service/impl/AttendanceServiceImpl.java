package com.alex.attendance.service.impl;

import com.alex.attendance.entity.Attendance;
import com.alex.attendance.mapper.AttendanceMapper;
import com.alex.attendance.service.AttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出勤 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {

}
