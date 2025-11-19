package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ExamSession;
import com.ruoyi.system.mapper.ExamSessionMapper;
import com.ruoyi.system.service.IExamSessionService;

/**
 * 考试场次 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class ExamSessionServiceImpl implements IExamSessionService
{
    @Autowired
    private ExamSessionMapper examSessionMapper;

    /**
     * 查询考试场次列表
     * 
     * @param examSession 考试场次
     * @return 考试场次集合
     */
    @Override
    public List<ExamSession> selectExamSessionList(ExamSession examSession)
    {
        return examSessionMapper.selectExamSessionList(examSession);
    }

    /**
     * 通过场次ID查询考试场次
     * 
     * @param sessionId 场次ID
     * @return 考试场次对象
     */
    @Override
    public ExamSession selectExamSessionById(Long sessionId)
    {
        return examSessionMapper.selectExamSessionById(sessionId);
    }

    /**
     * 新增考试场次
     * 
     * @param examSession 考试场次
     * @return 结果
     */
    @Override
    public int insertExamSession(ExamSession examSession)
    {
        return examSessionMapper.insertExamSession(examSession);
    }

    /**
     * 修改考试场次
     * 
     * @param examSession 考试场次
     * @return 结果
     */
    @Override
    public int updateExamSession(ExamSession examSession)
    {
        return examSessionMapper.updateExamSession(examSession);
    }

    /**
     * 批量删除考试场次
     * 
     * @param sessionIds 需要删除的场次ID
     * @return 结果
     */
    @Override
    public int deleteExamSessionByIds(Long[] sessionIds)
    {
        return examSessionMapper.deleteExamSessionByIds(sessionIds);
    }

    /**
     * 删除考试场次信息
     * 
     * @param sessionId 场次ID
     * @return 结果
     */
    @Override
    public int deleteExamSessionById(Long sessionId)
    {
        return examSessionMapper.deleteExamSessionById(sessionId);
    }

    /**
     * 校验场次编码是否唯一
     * 
     * @param examSession 考试场次
     * @return 结果
     */
    @Override
    public boolean checkSessionCodeUnique(ExamSession examSession)
    {
        Long sessionId = StringUtils.isNull(examSession.getSessionId()) ? -1L : examSession.getSessionId();
        ExamSession info = examSessionMapper.checkSessionCodeUnique(examSession.getSessionCode());
        if (StringUtils.isNotNull(info) && info.getSessionId().longValue() != sessionId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
