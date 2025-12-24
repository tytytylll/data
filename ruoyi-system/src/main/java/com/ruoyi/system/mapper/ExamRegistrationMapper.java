package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ExamRegistration;

/**
 * 考试报名Mapper接口
 */
public interface ExamRegistrationMapper
{
    public List<ExamRegistration> selectMyRegistrationList(Long userId);
    
    public int insertExamRegistration(ExamRegistration registration);
    
    public int updateExamRegistration(ExamRegistration registration);
    
    public int deleteExamRegistrationById(Long registrationId);
}
