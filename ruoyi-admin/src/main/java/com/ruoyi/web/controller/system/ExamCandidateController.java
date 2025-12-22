package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ExamCandidate;
import com.ruoyi.system.service.IExamCandidateService;

/**
 * 考生信息操作处理
 */
@RestController
@RequestMapping("/system/exam/candidate")
public class ExamCandidateController extends BaseController {
    @Autowired
    private IExamCandidateService examCandidateService;

    @PreAuthorize("@ss.hasPermi('system:candidate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamCandidate examCandidate) {
        startPage();
        List<ExamCandidate> list = examCandidateService.selectExamCandidateList(examCandidate);
        return getDataTable(list);
    }

    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        List<ExamCandidate> list = examCandidateService.selectExamCandidateAll();
        return success(list);
    }

    @Log(title = "考生信息", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:candidate:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamCandidate examCandidate) {
        List<ExamCandidate> list = examCandidateService.selectExamCandidateList(examCandidate);
        ExcelUtil<ExamCandidate> util = new ExcelUtil<ExamCandidate>(ExamCandidate.class);
        util.exportExcel(response, list, "考生数据");
    }

    @PreAuthorize("@ss.hasPermi('system:candidate:query')")
    @GetMapping(value = "/{candidateId}")
    public AjaxResult getInfo(@PathVariable Long candidateId) {
        return success(examCandidateService.selectExamCandidateById(candidateId));
    }

    @PreAuthorize("@ss.hasPermi('system:candidate:add')")
    @Log(title = "考生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ExamCandidate examCandidate) {
        if (!examCandidateService.checkIdCardUnique(examCandidate)) {
            return error("新增考生'" + examCandidate.getCandidateName() + "'失败，身份证号已存在");
        }
        examCandidate.setCreateBy(getUsername());
        return toAjax(examCandidateService.insertExamCandidate(examCandidate));
    }

    @PreAuthorize("@ss.hasPermi('system:candidate:edit')")
    @Log(title = "考生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ExamCandidate examCandidate) {
        if (!examCandidateService.checkIdCardUnique(examCandidate)) {
            return error("修改考生'" + examCandidate.getCandidateName() + "'失败，身份证号已存在");
        }
        examCandidate.setUpdateBy(getUsername());
        return toAjax(examCandidateService.updateExamCandidate(examCandidate));
    }

    @PreAuthorize("@ss.hasPermi('system:candidate:remove')")
    @Log(title = "考生信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{candidateIds}")
    public AjaxResult remove(@PathVariable Long[] candidateIds) {
        return toAjax(examCandidateService.deleteExamCandidateByIds(candidateIds));
    }
}
