package com.ruoyi.system.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.system.domain.ExamScore;

/**
 * 考试成绩 服务层
 * 
 * @author ruoyi
 */
public interface IExamScoreService
{
    /**
     * 查询成绩列表
     */
    public List<ExamScore> selectExamScoreList(ExamScore examScore);

    /**
     * 查询用户成绩列表（已发布的）
     */
    public List<ExamScore> selectMyExamScoreList(Long userId);

    /**
     * 通过成绩ID查询成绩
     */
    public ExamScore selectExamScoreById(Long scoreId);

    /**
     * 新增成绩
     */
    public int insertExamScore(ExamScore examScore);

    /**
     * 修改成绩
     */
    public int updateExamScore(ExamScore examScore);

    /**
     * 批量删除成绩
     */
    public int deleteExamScoreByIds(Long[] scoreIds);

    /**
     * 删除成绩信息
     */
    public int deleteExamScoreById(Long scoreId);

    /**
     * 批量发布成绩
     */
    public int publishExamScoreByIds(Long[] scoreIds);

    /**
     * 批量取消发布成绩
     */
    public int unpublishExamScoreByIds(Long[] scoreIds);

    /**
     * 设置定时发布
     */
    public int setScheduledPublish(Long[] scoreIds, Date scheduledTime);

    /**
     * 取消定时发布
     */
    public int cancelScheduledPublish(Long[] scoreIds);

    /**
     * 执行定时发布任务
     */
    public void executeScheduledPublish();
}
