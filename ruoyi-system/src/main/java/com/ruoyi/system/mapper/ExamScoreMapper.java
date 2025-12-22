package com.ruoyi.system.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.ExamScore;

/**
 * 考试成绩 数据层
 * 
 * @author ruoyi
 */
public interface ExamScoreMapper
{
    /**
     * 查询成绩列表
     */
    public List<ExamScore> selectExamScoreList(ExamScore examScore);

    /**
     * 查询用户成绩列表
     */
    public List<ExamScore> selectExamScoreByUserId(Long userId);

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
     * 删除成绩
     */
    public int deleteExamScoreById(Long scoreId);

    /**
     * 批量删除成绩
     */
    public int deleteExamScoreByIds(Long[] scoreIds);

    /**
     * 批量发布成绩
     */
    public int publishExamScoreByIds(Long[] scoreIds);

    /**
     * 批量取消发布成绩
     */
    public int unpublishExamScoreByIds(Long[] scoreIds);

    /**
     * 查询待定时发布的成绩
     */
    public List<ExamScore> selectScheduledScores();

    /**
     * 设置定时发布时间
     */
    public int setScheduledPublishTime(@Param("scoreIds") Long[] scoreIds, @Param("scheduledTime") Date scheduledTime);

    /**
     * 取消定时发布
     */
    public int cancelScheduledPublish(Long[] scoreIds);
}
