package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizReservationMapper;
import com.ruoyi.system.domain.BizReservation;
import com.ruoyi.system.service.IBizReservationService;

/**
 * 设备预约Service业务层处理
 *
 * @author ziyuan
 * @date 2026-08-21
 */
@Service
public class BizReservationServiceImpl implements IBizReservationService
{
    @Autowired
    private BizReservationMapper bizReservationMapper;

    /**
     */
    @Override
    public BizReservation selectBizReservationByReservationId(Long reservationId)
    {
        return bizReservationMapper.selectBizReservationByReservationId(reservationId);
    }

    /**
     */
    @Override
    public List<BizReservation> selectBizReservationList(BizReservation bizReservation)
    {
        return bizReservationMapper.selectBizReservationList(bizReservation);
    }

    /**
     */
    @Override
    public int insertBizReservation(BizReservation bizReservation)
    {
        // 基础时间校验
        if (!isValidTimeRange(bizReservation))
        {
            return 0;
        }

        // 检查设备预约时间是否冲突
        if (bizReservationMapper.countReservationConflict(bizReservation) > 0)
        {
            return 0;
        }

        // 新预约默认进入待审批状态
        if (bizReservation.getStatus() == null || bizReservation.getStatus().isEmpty())
        {
            bizReservation.setStatus("0");
        }

        bizReservation.setCreateTime(DateUtils.getNowDate());
        return bizReservationMapper.insertBizReservation(bizReservation);
    }

    /**
     */
    @Override
    public int updateBizReservation(BizReservation bizReservation)
    {
        BizReservation oldReservation =
                bizReservationMapper.selectBizReservationByReservationId(
                        bizReservation.getReservationId());

        if (oldReservation == null)
        {
            return 0;
        }

        // 已取消、已拒绝、已完成的预约不再允许修改
        if ("2".equals(oldReservation.getStatus())
                || "3".equals(oldReservation.getStatus())
                || "4".equals(oldReservation.getStatus()))
        {
            return 0;
        }

        // 时间校验
        if (!isValidTimeRange(bizReservation))
        {
            return 0;
        }

        // 修改预约时排除当前预约自身
        if (bizReservationMapper.countReservationConflict(bizReservation) > 0)
        {
            return 0;
        }

        bizReservation.setUpdateTime(DateUtils.getNowDate());
        return bizReservationMapper.updateBizReservation(bizReservation);
    }

    /**
     */
    private boolean isValidTimeRange(BizReservation bizReservation)
    {
        if (bizReservation.getDeviceId() == null
                || bizReservation.getUserId() == null
                || bizReservation.getStartTime() == null
                || bizReservation.getEndTime() == null)
        {
            return false;
        }

        // 结束时间必须晚于开始时间
        return bizReservation.getEndTime().after(bizReservation.getStartTime());
    }

    /**
     * 审批通过设备预约
     */
    @Override
    public int approveBizReservation(Long reservationId)
    {
        BizReservation reservation =
                bizReservationMapper.selectBizReservationByReservationId(reservationId);

        if (reservation == null)
        {
            return 0;
        }

        // 只有待审批状态才能通过
        if (!"0".equals(reservation.getStatus()))
        {
            return 0;
        }

        return bizReservationMapper.approveBizReservation(reservationId);
    }

    /**
     * 拒绝设备预约
     */
    @Override
    public int rejectBizReservation(Long reservationId)
    {
        BizReservation reservation =
                bizReservationMapper.selectBizReservationByReservationId(reservationId);

        if (reservation == null)
        {
            return 0;
        }

        // 只有待审批状态才能拒绝
        if (!"0".equals(reservation.getStatus()))
        {
            return 0;
        }

        return bizReservationMapper.rejectBizReservation(reservationId);
    }

    /**
     * 删除设备预约
     */
    @Override
    public int deleteBizReservationByReservationId(Long reservationId)
    {
        return bizReservationMapper.deleteBizReservationByReservationId(reservationId);
    }

    /**
     * 批量删除设备预约
     */
    @Override
    public int deleteBizReservationByReservationIds(Long[] reservationIds)
    {
        return bizReservationMapper.deleteBizReservationByReservationIds(reservationIds);
    }
}
