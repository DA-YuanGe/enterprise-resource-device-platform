package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.BizResourceMapper;
import com.ruoyi.system.domain.BizResource;
import com.ruoyi.system.service.IBizResourceService;

/**
 * 资源管理Service业务层处理
 *
 * @author ziyuan
 * @date 2026-08-22
 */
@Service
public class BizResourceServiceImpl implements IBizResourceService
{
    /**
     * 资源详情缓存前缀
     */
    private static final String RESOURCE_CACHE_KEY = "biz:resource:";

    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询资源管理
     *
     * @param resourceId 资源管理主键
     * @return 资源管理
     */
    @Override
    public BizResource selectBizResourceByResourceId(Long resourceId)
    {
        String cacheKey = RESOURCE_CACHE_KEY + resourceId;

        // 先查询 Redis
        BizResource cachedResource = redisCache.getCacheObject(cacheKey);
        if (cachedResource != null)
        {
            return cachedResource;
        }

        // Redis 未命中，查询 MySQL
        BizResource resource = bizResourceMapper.selectBizResourceByResourceId(resourceId);

        // 查询到资源后写入 Redis，缓存 30 分钟
        if (resource != null)
        {
            redisCache.setCacheObject(
                    cacheKey,
                    resource,
                    30,
                    TimeUnit.MINUTES
            );
        }

        return resource;
    }

    /**
     * 查询资源管理列表
     *
     * @param bizResource 资源管理
     * @return 资源管理集合
     */
    @Override
    public List<BizResource> selectBizResourceList(BizResource bizResource)
    {
        return bizResourceMapper.selectBizResourceList(bizResource);
    }

    /**
     * 新增资源管理
     *
     * @param bizResource 资源管理
     * @return 结果
     */
    @Override
    public int insertBizResource(BizResource bizResource)
    {
        bizResource.setCreateTime(DateUtils.getNowDate());
        return bizResourceMapper.insertBizResource(bizResource);
    }

    /**
     * 修改资源管理
     *
     * @param bizResource 资源管理
     * @return 结果
     */
    @Override
    public int updateBizResource(BizResource bizResource)
    {
        bizResource.setUpdateTime(DateUtils.getNowDate());

        int result = bizResourceMapper.updateBizResource(bizResource);

        // 数据库修改成功后删除 Redis 缓存
        if (result > 0)
        {
            redisCache.deleteObject(RESOURCE_CACHE_KEY + bizResource.getResourceId());
        }

        return result;
    }

    /**
     * 批量删除资源管理
     *
     * @param resourceIds 需要删除的资源主键集合
     * @return 结果
     */
    @Override
    public int deleteBizResourceByResourceIds(Long[] resourceIds)
    {
        int result = bizResourceMapper.deleteBizResourceByResourceIds(resourceIds);

        if (result > 0 && resourceIds != null)
        {
            for (Long resourceId : resourceIds)
            {
                redisCache.deleteObject(RESOURCE_CACHE_KEY + resourceId);
            }
        }

        return result;
    }

    /**
     * 删除资源管理信息
     *
     * @param resourceId 资源管理主键
     * @return 结果
     */
    @Override
    public int deleteBizResourceByResourceId(Long resourceId)
    {
        int result = bizResourceMapper.deleteBizResourceByResourceId(resourceId);

        if (result > 0)
        {
            redisCache.deleteObject(RESOURCE_CACHE_KEY + resourceId);
        }

        return result;
    }
}
