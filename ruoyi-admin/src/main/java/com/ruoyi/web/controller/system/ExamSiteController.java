package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ExamSite;
import com.ruoyi.system.service.IExamSiteService;

/**
 * 考点操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/exam/site")
public class ExamSiteController extends BaseController
{
    @Autowired
    private IExamSiteService examSiteService;

    /**
     * 获取考点列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamSite examSite)
    {
        startPage();
        List<ExamSite> list = examSiteService.selectExamSiteList(examSite);
        return getDataTable(list);
    }
    
    /**
     * 导出考点列表
     */
    @Log(title = "考点管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:site:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamSite examSite)
    {
        List<ExamSite> list = examSiteService.selectExamSiteList(examSite);
        ExcelUtil<ExamSite> util = new ExcelUtil<ExamSite>(ExamSite.class);
        util.exportExcel(response, list, "考点数据");
    }

    /**
     * 根据考点ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:site:query')")
    @GetMapping(value = "/{siteId}")
    public AjaxResult getInfo(@PathVariable Long siteId)
    {
        return success(examSiteService.selectExamSiteById(siteId));
    }

    /**
     * 新增考点
     */
    @PreAuthorize("@ss.hasPermi('system:site:add')")
    @Log(title = "考点管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ExamSite examSite)
    {
        if (!examSiteService.checkSiteNameUnique(examSite))
        {
            return error("新增考点'" + examSite.getSiteName() + "'失败，考点名称已存在");
        }
        else if (!examSiteService.checkSiteCodeUnique(examSite))
        {
            return error("新增考点'" + examSite.getSiteName() + "'失败，考点编码已存在");
        }
        examSite.setCreateBy(getUsername());
        return toAjax(examSiteService.insertExamSite(examSite));
    }

    /**
     * 修改考点
     */
    @PreAuthorize("@ss.hasPermi('system:site:edit')")
    @Log(title = "考点管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ExamSite examSite)
    {
        if (!examSiteService.checkSiteNameUnique(examSite))
        {
            return error("修改考点'" + examSite.getSiteName() + "'失败，考点名称已存在");
        }
        else if (!examSiteService.checkSiteCodeUnique(examSite))
        {
            return error("修改考点'" + examSite.getSiteName() + "'失败，考点编码已存在");
        }
        examSite.setUpdateBy(getUsername());
        return toAjax(examSiteService.updateExamSite(examSite));
    }

    /**
     * 删除考点
     */
    @PreAuthorize("@ss.hasPermi('system:site:remove')")
    @Log(title = "考点管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{siteIds}")
    public AjaxResult remove(@PathVariable Long[] siteIds)
    {
        return toAjax(examSiteService.deleteExamSiteByIds(siteIds));
    }

    /**
     * 获取考点选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<ExamSite> sites = examSiteService.selectExamSiteAll();
        return success(sites);
    }
}
