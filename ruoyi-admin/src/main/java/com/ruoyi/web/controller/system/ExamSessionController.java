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
import com.ruoyi.system.domain.ExamSession;
import com.ruoyi.system.service.IExamSessionService;

/**
 * 考试场次操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/exam/session")
public class ExamSessionController extends BaseController
{
    @Autowired
    private IExamSessionService examSessionService;

    /**
     * 获取考试场次列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamSession examSession)
    {
        startPage();
        List<ExamSession> list = examSessionService.selectExamSessionList(examSession);
        return getDataTable(list);
    }
    
    /**
     * 导出考试场次列表
     */
    @Log(title = "考试场次", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:session:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamSession examSession)
    {
        List<ExamSession> list = examSessionService.selectExamSessionList(examSession);
        ExcelUtil<ExamSession> util = new ExcelUtil<ExamSession>(ExamSession.class);
        util.exportExcel(response, list, "考试场次数据");
    }

    /**
     * 根据场次ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:session:query')")
    @GetMapping(value = "/{sessionId}")
    public AjaxResult getInfo(@PathVariable Long sessionId)
    {
        return success(examSessionService.selectExamSessionById(sessionId));
    }

    /**
     * 新增考试场次
     */
    @PreAuthorize("@ss.hasPermi('system:session:add')")
    @Log(title = "考试场次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ExamSession examSession)
    {
        if (!examSessionService.checkSessionCodeUnique(examSession))
        {
            return error("新增考试场次'" + examSession.getSessionName() + "'失败，场次编码已存在");
        }
        examSession.setCreateBy(getUsername());
        return toAjax(examSessionService.insertExamSession(examSession));
    }

    /**
     * 修改考试场次
     */
    @PreAuthorize("@ss.hasPermi('system:session:edit')")
    @Log(title = "考试场次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ExamSession examSession)
    {
        if (!examSessionService.checkSessionCodeUnique(examSession))
        {
            return error("修改考试场次'" + examSession.getSessionName() + "'失败，场次编码已存在");
        }
        examSession.setUpdateBy(getUsername());
        return toAjax(examSessionService.updateExamSession(examSession));
    }

    /**
     * 删除考试场次
     */
    @PreAuthorize("@ss.hasPermi('system:session:remove')")
    @Log(title = "考试场次", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sessionIds}")
    public AjaxResult remove(@PathVariable Long[] sessionIds)
    {
        return toAjax(examSessionService.deleteExamSessionByIds(sessionIds));
    }
}
