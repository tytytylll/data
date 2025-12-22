package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ExamCandidate;
import com.ruoyi.system.mapper.ExamCandidateMapper;
import com.ruoyi.system.service.IExamCandidateService;

/**
 * 考生信息 服务层处理
 */
@Service
public class ExamCandidateServiceImpl implements IExamCandidateService {
    @Autowired
    private ExamCandidateMapper examCandidateMapper;

    @Override
    public List<ExamCandidate> selectExamCandidateList(ExamCandidate examCandidate) {
        return examCandidateMapper.selectExamCandidateList(examCandidate);
    }

    @Override
    public List<ExamCandidate> selectExamCandidateAll() {
        return examCandidateMapper.selectExamCandidateAll();
    }

    @Override
    public ExamCandidate selectExamCandidateById(Long candidateId) {
        return examCandidateMapper.selectExamCandidateById(candidateId);
    }

    @Override
    public boolean checkIdCardUnique(ExamCandidate examCandidate) {
        Long candidateId = StringUtils.isNull(examCandidate.getCandidateId()) ? -1L : examCandidate.getCandidateId();
        ExamCandidate info = examCandidateMapper.checkIdCardUnique(examCandidate.getIdCard());
        if (StringUtils.isNotNull(info) && info.getCandidateId().longValue() != candidateId.longValue()) {
            return false;
        }
        return true;
    }

    @Override
    public int insertExamCandidate(ExamCandidate examCandidate) {
        return examCandidateMapper.insertExamCandidate(examCandidate);
    }

    @Override
    public int updateExamCandidate(ExamCandidate examCandidate) {
        return examCandidateMapper.updateExamCandidate(examCandidate);
    }

    @Override
    public int deleteExamCandidateById(Long candidateId) {
        return examCandidateMapper.deleteExamCandidateById(candidateId);
    }

    @Override
    public int deleteExamCandidateByIds(Long[] candidateIds) {
        return examCandidateMapper.deleteExamCandidateByIds(candidateIds);
    }
}
