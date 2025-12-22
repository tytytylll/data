package com.ruoyi.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.system.service.IExamScoreService;

/**
 * 考试成绩定时任务
 * 
 * @author ruoyi
 */
@Component("examScoreTask")
public class ExamScoreTask
{
    @Autowired
    private IExamScoreService examScoreService;

    /**
     * 定时发布成绩任务
     * 检查并发布已到定时发布时间的成绩
     */
    public void publishScheduledScores()
    {
        examScoreService.executeScheduledPublish();
    }
}
