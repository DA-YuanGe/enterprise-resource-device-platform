package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BizDeviceMapper;
import com.ruoyi.system.domain.BizDevice;
import com.ruoyi.system.service.IBizDeviceService;

/**
 * 设备管理Service业务层处理
 * 
 * @author ziyuan
 * @date 2026-08-20
 */
@Service
public class BizDeviceServiceImpl implements IBizDeviceService 
{
    @Autowired
    private BizDeviceMapper bizDeviceMapper;

    /**
     * 查询设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理
     */
    @Override
    public BizDevice selectBizDeviceByDeviceId(Long deviceId)
    {
        return bizDeviceMapper.selectBizDeviceByDeviceId(deviceId);
    }

    /**
     * 查询设备管理列表
     * 
     * @param bizDevice 设备管理
     * @return 设备管理
     */
    @Override
    public List<BizDevice> selectBizDeviceList(BizDevice bizDevice)
    {
        return bizDeviceMapper.selectBizDeviceList(bizDevice);
    }

    /**
     * 新增设备管理
     * 
     * @param bizDevice 设备管理
     * @return 结果
     */
    @Override
    public int insertBizDevice(BizDevice bizDevice)
    {
        bizDevice.setCreateTime(DateUtils.getNowDate());
        return bizDeviceMapper.insertBizDevice(bizDevice);
    }

    /**
     * 修改设备管理
     * 
     * @param bizDevice 设备管理
     * @return 结果
     */
   @Override
public int updateBizDevice(BizDevice bizDevice)
{
    // 查询数据库中的原设备信息
    BizDevice oldDevice = bizDeviceMapper.selectBizDeviceByDeviceId(bizDevice.getDeviceId());

    // 设备不存在
    if (oldDevice == null)
    {
        return 0;
    }

    // 获取修改前后的状态
    String oldStatus = oldDevice.getStatus();
    String newStatus = bizDevice.getStatus();

    // 状态发生变化时，检查状态流转是否合法
    if (newStatus != null && !newStatus.equals(oldStatus))
    {
        if (!isValidStatusTransition(oldStatus, newStatus))
        {
            return 0;
        }
    }

    bizDevice.setUpdateTime(DateUtils.getNowDate());
    return bizDeviceMapper.updateBizDevice(bizDevice);
}
/**
 * 判断设备状态流转是否合法
 *
 * @param oldStatus 原状态
 * @param newStatus 新状态
 * @return true 合法，false 不合法
 */
private boolean isValidStatusTransition(String oldStatus, String newStatus)
{
    if (oldStatus == null || newStatus == null)
    {
        return false;
    }

    switch (oldStatus)
    {
        case "0":
            return "1".equals(newStatus)
                    || "2".equals(newStatus)
                    || "3".equals(newStatus);

        case "1":
            return "0".equals(newStatus)
                    || "2".equals(newStatus);

        case "2":
            return "0".equals(newStatus)
                    || "3".equals(newStatus);

        case "3":
            return "0".equals(newStatus);

        default:
            return false;
    }
}
    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteBizDeviceByDeviceIds(Long[] deviceIds)
    {
        return bizDeviceMapper.deleteBizDeviceByDeviceIds(deviceIds);
    }

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteBizDeviceByDeviceId(Long deviceId)
    {
        return bizDeviceMapper.deleteBizDeviceByDeviceId(deviceId);
    }
}
