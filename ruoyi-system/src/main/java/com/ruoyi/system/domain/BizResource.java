package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资源管理对象 biz_resource
 *
 * @author ziyuan
 * @date 2026-08-22
 */
public class BizResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private Long resourceId;

    /** 资源编号 */
    @Excel(name = "资源编号")
    private String resourceCode;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String resourceName;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String resourceType;

    /** 数量 */
    @Excel(name = "数量")
    private Integer quantity;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 存放位置 */
    @Excel(name = "存放位置")
    private String location;

    /** 资源状态：0正常 1使用中 2维修中 3停用 */
    @Excel(name = "资源状态：0正常 1使用中 2维修中 3停用")
    private String status;

    /** 负责人用户ID */
    @Excel(name = "负责人用户ID")
    private Long managerId;

    /** 资源描述 */
    @Excel(name = "资源描述")
    private String description;

    public void setResourceId(Long resourceId)
    {
        this.resourceId = resourceId;
    }

    public Long getResourceId()
    {
        return resourceId;
    }

    public void setResourceCode(String resourceCode)
    {
        this.resourceCode = resourceCode;
    }

    public String getResourceCode()
    {
        return resourceCode;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setManagerId(Long managerId)
    {
        this.managerId = managerId;
    }

    public Long getManagerId()
    {
        return managerId;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("resourceId", getResourceId())
            .append("resourceCode", getResourceCode())
            .append("resourceName", getResourceName())
            .append("resourceType", getResourceType())
            .append("quantity", getQuantity())
            .append("unit", getUnit())
            .append("location", getLocation())
            .append("status", getStatus())
            .append("managerId", getManagerId())
            .append("description", getDescription())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
