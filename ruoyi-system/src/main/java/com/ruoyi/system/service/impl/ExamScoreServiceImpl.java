package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.ExamScore;
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
    @Autowired
    private ExamScoreMapper examScoreMapper;

    @Override
    public List<ExamScore> selectExamScoreList(ExamScore examScore)
    {
        return examScoreMapper.selectExamScoreList(examScore);
    }

    @Override
    public List<ExamScore> selectMyExamScoreList(Long userId)
    {
        return examScoreMapper.selectExamScoreByUserId(userId);
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
}
