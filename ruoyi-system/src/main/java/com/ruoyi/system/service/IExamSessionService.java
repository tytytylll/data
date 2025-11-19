package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ExamSession;

/**
 * 考试场次 服务层
 * 
 * @author ruoyi
 */
public interface IExamSessionService
{
    /**
     * 查询考试场次列表
     * 
     * @param examSession 考试场次
     * @return 考试场次集合
     */
    public List<ExamSession> selectExamSessionList(ExamSession examSession);

    /**
     * 通过场次ID查询考试场次
     * 
     * @param sessionId 场次ID
     * @return 考试场次对象
     */
    public ExamSession selectExamSessionById(Long sessionId);

    /**
     * 新增考试场次
     * 
     * @param examSession 考试场次
     * @return 结果
     */
    public int insertExamSession(ExamSession examSession);

    /**
     * 修改考试场次
     * 
     * @param examSession 考试场次
     * @return 结果
     */
    public int updateExamSession(ExamSession examSession);

    /**
     * 批量删除考试场次
     * 
     * @param sessionIds 需要删除的场次ID
     * @return 结果
     */
    public int deleteExamSessionByIds(Long[] sessionIds);

    /**
     * 删除考试场次信息
     * 
     * @param sessionId 场次ID
     * @return 结果
     */
    public int deleteExamSessionById(Long sessionId);

    /**
     * 校验场次编码是否唯一
     * 
     * @param examSession 考试场次
     * @return 结果
     */
    public boolean checkSessionCodeUnique(ExamSession examSession);
}
