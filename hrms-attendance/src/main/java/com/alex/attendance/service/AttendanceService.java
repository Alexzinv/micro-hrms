package com.alex.attendance.service;

import com.alex.attendance.entity.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 出勤 服务类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
public interface AttendanceService extends IService<Attendance> {

    /**
     * 根据用户id查询出勤信息
     * @param userId 用户id
     * @return 考勤信息
     */
    List<Attendance> listByUserId(Long userId);
}
