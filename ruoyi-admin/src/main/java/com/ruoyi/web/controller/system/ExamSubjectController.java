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
import com.ruoyi.system.domain.ExamSubject;
import com.ruoyi.system.service.IExamSubjectService;

/**
 * 考试科目操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/exam/subject")
public class ExamSubjectController extends BaseController
{
    @Autowired
    private IExamSubjectService examSubjectService;

    /**
     * 获取考试科目列表
     */
    @PreAuthorize("@ss.hasPermi('system:subject:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamSubject examSubject)
    {
        startPage();
        List<ExamSubject> list = examSubjectService.selectExamSubjectList(examSubject);
        return getDataTable(list);
    }
    
    /**
     * 导出考试科目列表
     */
    @Log(title = "考试科目", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:subject:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamSubject examSubject)
    {
        List<ExamSubject> list = examSubjectService.selectExamSubjectList(examSubject);
        ExcelUtil<ExamSubject> util = new ExcelUtil<ExamSubject>(ExamSubject.class);
        util.exportExcel(response, list, "考试科目数据");
    }

    /**
     * 根据科目ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:subject:query')")
    @GetMapping(value = "/{subjectId}")
    public AjaxResult getInfo(@PathVariable Long subjectId)
    {
        return success(examSubjectService.selectExamSubjectById(subjectId));
    }

    /**
     * 新增考试科目
     */
    @PreAuthorize("@ss.hasPermi('system:subject:add')")
    @Log(title = "考试科目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ExamSubject examSubject)
    {
        if (!examSubjectService.checkSubjectNameUnique(examSubject))
        {
            return error("新增考试科目'" + examSubject.getSubjectName() + "'失败，科目名称已存在");
        }
        else if (!examSubjectService.checkSubjectCodeUnique(examSubject))
        {
            return error("新增考试科目'" + examSubject.getSubjectName() + "'失败，科目编码已存在");
        }
        examSubject.setCreateBy(getUsername());
        return toAjax(examSubjectService.insertExamSubject(examSubject));
    }

    /**
     * 修改考试科目
     */
    @PreAuthorize("@ss.hasPermi('system:subject:edit')")
    @Log(title = "考试科目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ExamSubject examSubject)
    {
        if (!examSubjectService.checkSubjectNameUnique(examSubject))
        {
            return error("修改考试科目'" + examSubject.getSubjectName() + "'失败，科目名称已存在");
        }
        else if (!examSubjectService.checkSubjectCodeUnique(examSubject))
        {
            return error("修改考试科目'" + examSubject.getSubjectName() + "'失败，科目编码已存在");
        }
        examSubject.setUpdateBy(getUsername());
        return toAjax(examSubjectService.updateExamSubject(examSubject));
    }

    /**
     * 删除考试科目
     */
    @PreAuthorize("@ss.hasPermi('system:subject:remove')")
    @Log(title = "考试科目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{subjectIds}")
    public AjaxResult remove(@PathVariable Long[] subjectIds)
    {
        return toAjax(examSubjectService.deleteExamSubjectByIds(subjectIds));
    }

    /**
     * 获取考试科目选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<ExamSubject> subjects = examSubjectService.selectExamSubjectAll();
        return success(subjects);
    }
}
