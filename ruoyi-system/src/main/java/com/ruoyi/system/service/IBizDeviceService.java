package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BizDevice;

/**
 * 设备管理Service接口
 * 
 * @author ziyuan
 * @date 2026-08-20
 */
public interface IBizDeviceService 
{
    /**
     * 查询设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理
     */
    public BizDevice selectBizDeviceByDeviceId(Long deviceId);

    /**
     * 查询设备管理列表
     * 
     * @param bizDevice 设备管理
     * @return 设备管理集合
     */
    public List<BizDevice> selectBizDeviceList(BizDevice bizDevice);

    /**
     * 新增设备管理
     * 
     * @param bizDevice 设备管理
     * @return 结果
     */
    public int insertBizDevice(BizDevice bizDevice);

    /**
     * 修改设备管理
     * 
     * @param bizDevice 设备管理
     * @return 结果
     */
    public int updateBizDevice(BizDevice bizDevice);

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteBizDeviceByDeviceIds(Long[] deviceIds);

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteBizDeviceByDeviceId(Long deviceId);
}
