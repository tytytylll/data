package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ExamCandidate;

/**
 * 考生信息 服务层
 */
public interface IExamCandidateService {
    public List<ExamCandidate> selectExamCandidateList(ExamCandidate examCandidate);
    public List<ExamCandidate> selectExamCandidateAll();
    public ExamCandidate selectExamCandidateById(Long candidateId);
    public boolean checkIdCardUnique(ExamCandidate examCandidate);
    public int insertExamCandidate(ExamCandidate examCandidate);
    public int updateExamCandidate(ExamCandidate examCandidate);
    public int deleteExamCandidateById(Long candidateId);
    public int deleteExamCandidateByIds(Long[] candidateIds);
}
