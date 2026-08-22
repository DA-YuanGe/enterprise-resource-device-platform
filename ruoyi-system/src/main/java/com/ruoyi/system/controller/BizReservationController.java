package com.ruoyi.system.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import com.ruoyi.system.domain.BizReservation;
import com.ruoyi.system.service.IBizReservationService;

/**
 * 设备预约Controller
 *
 * @author ziyuan
 * @date 2026-08-21
 */
@RestController
@RequestMapping("/system/reservation")
public class BizReservationController extends BaseController
{
    @Autowired
    private IBizReservationService bizReservationService;

    /**
     * 查询设备预约列表
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizReservation bizReservation)
    {
        startPage();
        List<BizReservation> list = bizReservationService.selectBizReservationList(bizReservation);
        return getDataTable(list);
    }

    /**
     * 导出设备预约列表
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:export')")
    @Log(title = "设备预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizReservation bizReservation)
    {
        List<BizReservation> list = bizReservationService.selectBizReservationList(bizReservation);
        ExcelUtil<BizReservation> util = new ExcelUtil<BizReservation>(BizReservation.class);
        util.exportExcel(response, list, "设备预约数据");
    }

    /**
     * 获取设备预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:query')")
    @GetMapping(value = "/{reservationId}")
    public AjaxResult getInfo(@PathVariable("reservationId") Long reservationId)
    {
        return success(bizReservationService.selectBizReservationByReservationId(reservationId));
    }

    /**
     * 新增设备预约
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:add')")
    @Log(title = "设备预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizReservation bizReservation)
    {
        return toAjax(bizReservationService.insertBizReservation(bizReservation));
    }

    /**
     * 修改设备预约
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:edit')")
    @Log(title = "设备预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizReservation bizReservation)
    {
        return toAjax(bizReservationService.updateBizReservation(bizReservation));
    }

    /**
     * 审批通过设备预约
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:approve')")
    @Log(title = "设备预约", businessType = BusinessType.UPDATE)
    @PostMapping("/approve/{reservationId}")
    public AjaxResult approve(@PathVariable("reservationId") Long reservationId)
    {
        return toAjax(bizReservationService.approveBizReservation(reservationId));
    }

    /**
     * 拒绝设备预约
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:approve')")
    @Log(title = "设备预约", businessType = BusinessType.UPDATE)
    @PostMapping("/reject/{reservationId}")
    public AjaxResult reject(@PathVariable("reservationId") Long reservationId)
    {
        return toAjax(bizReservationService.rejectBizReservation(reservationId));
    }

    /**
     * 删除设备预约
     */
    @PreAuthorize("@ss.hasPermi('system:reservation:remove')")
    @Log(title = "设备预约", businessType = BusinessType.DELETE)
    @DeleteMapping("/{reservationIds}")
    public AjaxResult remove(@PathVariable Long[] reservationIds)
    {
        return toAjax(bizReservationService.deleteBizReservationByReservationIds(reservationIds));
    }
}