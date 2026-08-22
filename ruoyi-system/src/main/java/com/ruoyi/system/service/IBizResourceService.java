package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BizResource;

/**
 * 资源管理Service接口
 *
 * @author ziyuan
 * @date 2026-08-22
 */
public interface IBizResourceService
{
    /**
     * 查询资源管理
     *
     * @param resourceId 资源管理主键
     * @return 资源管理
     */
    public BizResource selectBizResourceByResourceId(Long resourceId);

    /**
     * 查询资源管理列表
     *
     * @param bizResource 资源管理
     * @return 资源管理集合
     */
    public List<BizResource> selectBizResourceList(BizResource bizResource);

    /**
     * 新增资源管理
     *
     * @param bizResource 资源管理
     * @return 结果
     */
    public int insertBizResource(BizResource bizResource);

    /**
     * 修改资源管理
     *
     * @param bizResource 资源管理
     * @return 结果
     */
    public int updateBizResource(BizResource bizResource);

    /**
     * 批量删除资源管理
     *
     * @param resourceIds 需要删除的资源主键集合
     * @return 结果
     */
    public int deleteBizResourceByResourceIds(Long[] resourceIds);

    /**
     * 删除资源管理信息
     *
     * @param resourceId 资源管理主键
     * @return 结果
     */
    public int deleteBizResourceByResourceId(Long resourceId);
}
