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
import com.ruoyi.system.domain.ExamScore;
import com.ruoyi.system.service.IExamScoreService;

/**
 * 考试成绩操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/exam/score")
public class ExamScoreController extends BaseController
{
    @Autowired
    private IExamScoreService examScoreService;

    /**
     * 获取成绩列表（管理员）
     */
    @PreAuthorize("@ss.hasPermi('system:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamScore examScore)
    {
        startPage();
        List<ExamScore> list = examScoreService.selectExamScoreList(examScore);
        return getDataTable(list);
    }

    /**
     * 获取我的成绩列表（当前用户）
     */
    @GetMapping("/my")
    public TableDataInfo myList()
    {
        startPage();
        List<ExamScore> list = examScoreService.selectMyExamScoreList(getUserId());
        return getDataTable(list);
    }

    /**
     * 导出成绩列表
     */
    @Log(title = "考试成绩", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:score:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamScore examScore)
    {
        List<ExamScore> list = examScoreService.selectExamScoreList(examScore);
        ExcelUtil<ExamScore> util = new ExcelUtil<ExamScore>(ExamScore.class);
        util.exportExcel(response, list, "考试成绩数据");
    }

    /**
     * 根据成绩ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:score:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable Long scoreId)
    {
        return success(examScoreService.selectExamScoreById(scoreId));
    }

    /**
     * 新增成绩
     */
    @PreAuthorize("@ss.hasPermi('system:score:add')")
    @Log(title = "考试成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ExamScore examScore)
    {
        examScore.setCreateBy(getUsername());
        return toAjax(examScoreService.insertExamScore(examScore));
    }

    /**
     * 修改成绩
     */
    @PreAuthorize("@ss.hasPermi('system:score:edit')")
    @Log(title = "考试成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ExamScore examScore)
    {
        examScore.setUpdateBy(getUsername());
        return toAjax(examScoreService.updateExamScore(examScore));
    }

    /**
     * 删除成绩
     */
    @PreAuthorize("@ss.hasPermi('system:score:remove')")
    @Log(title = "考试成绩", businessType = BusinessType.DELETE)
    @DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(examScoreService.deleteExamScoreByIds(scoreIds));
    }

    /**
     * 发布成绩
     */
    @PreAuthorize("@ss.hasPermi('system:score:edit')")
    @Log(title = "考试成绩", businessType = BusinessType.UPDATE)
    @PutMapping("/publish/{scoreIds}")
    public AjaxResult publish(@PathVariable Long[] scoreIds)
    {
        return toAjax(examScoreService.publishExamScoreByIds(scoreIds));
    }
}
