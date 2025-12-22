package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.ExamCandidate;
import com.ruoyi.system.domain.ExamScore;
import com.ruoyi.system.mapper.ExamCandidateMapper;
import com.ruoyi.system.mapper.ExamScoreMapper;
import com.ruoyi.system.service.IExamScoreService;

/**
 * 考试成绩 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class ExamScoreServiceImpl implements IExamScoreService
{
    private static final Logger log = LoggerFactory.getLogger(ExamScoreServiceImpl.class);

    @Autowired
    private ExamScoreMapper examScoreMapper;

    @Autowired
    private ExamCandidateMapper examCandidateMapper;

    @Override
    public List<ExamScore> selectExamScoreList(ExamScore examScore)
    {
        return examScoreMapper.selectExamScoreList(examScore);
    }

    @Override
    public List<ExamScore> selectMyExamScoreList(Long userId, ExamScore examScore)
    {
        // 先通过用户ID查找关联的考生
        ExamCandidate candidate = examCandidateMapper.selectExamCandidateByUserId(userId);
        if (candidate != null)
        {
            examScore.setCandidateId(candidate.getCandidateId());
        }
        else
        {
            examScore.setUserId(userId);
        }
        // 只查询已发布的成绩
        examScore.setPublishStatus("1");
        examScore.setStatus("0");
        return examScoreMapper.selectMyExamScoreList(examScore);
    }

    @Override
    public ExamScore selectExamScoreById(Long scoreId)
    {
        return examScoreMapper.selectExamScoreById(scoreId);
    }

    @Override
    public int insertExamScore(ExamScore examScore)
    {
        return examScoreMapper.insertExamScore(examScore);
    }

    @Override
    public int updateExamScore(ExamScore examScore)
    {
        return examScoreMapper.updateExamScore(examScore);
    }

    @Override
    public int deleteExamScoreByIds(Long[] scoreIds)
    {
        return examScoreMapper.deleteExamScoreByIds(scoreIds);
    }

    @Override
    public int deleteExamScoreById(Long scoreId)
    {
        return examScoreMapper.deleteExamScoreById(scoreId);
    }

    @Override
    public int publishExamScoreByIds(Long[] scoreIds)
    {
        return examScoreMapper.publishExamScoreByIds(scoreIds);
    }

    @Override
    public int unpublishExamScoreByIds(Long[] scoreIds)
    {
        return examScoreMapper.unpublishExamScoreByIds(scoreIds);
    }

    @Override
    public int setScheduledPublish(Long[] scoreIds, Date scheduledTime)
    {
        return examScoreMapper.setScheduledPublishTime(scoreIds, scheduledTime);
    }

    @Override
    public int cancelScheduledPublish(Long[] scoreIds)
    {
        return examScoreMapper.cancelScheduledPublish(scoreIds);
    }

    @Override
    public void executeScheduledPublish()
    {
        List<ExamScore> scores = examScoreMapper.selectScheduledScores();
        if (scores != null && !scores.isEmpty())
        {
            Long[] scoreIds = scores.stream().map(ExamScore::getScoreId).toArray(Long[]::new);
            int count = examScoreMapper.publishExamScoreByIds(scoreIds);
            // 发布后清除定时发布时间
            examScoreMapper.cancelScheduledPublish(scoreIds);
            log.info("定时发布成绩任务执行完成，共发布{}条成绩", count);
        }
    }
}
