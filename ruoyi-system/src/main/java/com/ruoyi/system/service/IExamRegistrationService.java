package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ExamRegistration;

/**
 * 考试报名Service接口
 */
public interface IExamRegistrationService
{
    public List<ExamRegistration> selectMyRegistrationList(Long userId);
    
    public int insertExamRegistration(ExamRegistration registration);
    
    public int updateExamRegistration(ExamRegistration registration);
    
    public int deleteExamRegistrationById(Long registrationId);
}
