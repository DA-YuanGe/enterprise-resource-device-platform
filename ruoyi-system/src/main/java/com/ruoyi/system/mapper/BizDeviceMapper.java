package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BizDevice;

/**
 * 设备管理Mapper接口
 * 
 * @author ziyuan
 * @date 2026-08-20
 */
public interface BizDeviceMapper 
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
     * 删除设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteBizDeviceByDeviceId(Long deviceId);

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizDeviceByDeviceIds(Long[] deviceIds);
}
