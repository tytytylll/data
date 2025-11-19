package com.ruoyi.web.controller.system;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IExamSubjectService;
import com.ruoyi.system.service.IExamSiteService;
import com.ruoyi.system.service.IExamSessionService;

/**
 * 考试统计Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/exam/statistics")
public class ExamStatisticsController extends BaseController
{
    @Autowired
    private IExamSubjectService examSubjectService;
    
    @Autowired
    private IExamSiteService examSiteService;
    
    @Autowired
    private IExamSessionService examSessionService;

    /**
     * 获取首页统计数据
     */
    @GetMapping("/home")
    public AjaxResult getHomeStatistics()
    {
        Map<String, Object> stats = new HashMap<>();
        
        // 获取科目总数
        int subjectCount = examSubjectService.selectExamSubjectList(null).size();
        stats.put("subjectCount", subjectCount);
        
        // 获取考点总数
        int siteCount = examSiteService.selectExamSiteList(null).size();
        stats.put("siteCount", siteCount);
        
        // 获取场次总数
        int sessionCount = examSessionService.selectExamSessionList(null).size();
        stats.put("sessionCount", sessionCount);
        
        return AjaxResult.success(stats);
    }
}
