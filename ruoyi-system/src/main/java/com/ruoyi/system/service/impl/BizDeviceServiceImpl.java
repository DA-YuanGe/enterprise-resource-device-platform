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
        bizDevice.setUpdateTime(DateUtils.getNowDate());
        return bizDeviceMapper.updateBizDevice(bizDevice);
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
