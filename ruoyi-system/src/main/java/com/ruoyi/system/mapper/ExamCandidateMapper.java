package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ExamCandidate;

/**
 * 考生信息 数据层
 */
public interface ExamCandidateMapper {
    public List<ExamCandidate> selectExamCandidateList(ExamCandidate examCandidate);
    public List<ExamCandidate> selectExamCandidateAll();
    public ExamCandidate selectExamCandidateById(Long candidateId);
    public ExamCandidate checkIdCardUnique(String idCard);
    public int insertExamCandidate(ExamCandidate examCandidate);
    public int updateExamCandidate(ExamCandidate examCandidate);
    public int deleteExamCandidateById(Long candidateId);
    public int deleteExamCandidateByIds(Long[] candidateIds);
}
