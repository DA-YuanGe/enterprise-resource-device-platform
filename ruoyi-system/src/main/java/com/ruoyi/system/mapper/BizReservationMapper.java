package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BizReservation;

/**
 * 设备预约Mapper接口
 *
 * @author ziyuan
 * @date 2026-08-21
 */
public interface BizReservationMapper
{
    /**
     * 查询设备预约
     *
     * @param reservationId 预约主键
     * @return 设备预约
     */
    public BizReservation selectBizReservationByReservationId(Long reservationId);

    /**
     * 查询设备预约列表
     *
     * @param bizReservation 设备预约
     * @return 设备预约集合
     */
    public List<BizReservation> selectBizReservationList(BizReservation bizReservation);

    /**
     * 新增设备预约
     *
     * @param bizReservation 设备预约
     * @return 结果
     */
    public int insertBizReservation(BizReservation bizReservation);

    /**
     * 修改设备预约
     *
     * @param bizReservation 设备预约
     * @return 结果
     */
    public int updateBizReservation(BizReservation bizReservation);

    /**
     * 删除设备预约
     *
     * @param reservationId 预约主键
     * @return 结果
     */
    public int deleteBizReservationByReservationId(Long reservationId);

    /**
     * 批量删除设备预约
     *
     * @param reservationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizReservationByReservationIds(Long[] reservationIds);

    /**
     * 查询设备指定时间范围内是否存在冲突预约
     *
     * @param bizReservation 预约信息
     * @return 冲突数量
     */
    public int countReservationConflict(BizReservation bizReservation);
}
