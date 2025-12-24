package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.ExamRegistration;
import com.ruoyi.system.mapper.ExamRegistrationMapper;
import com.ruoyi.system.service.IExamRegistrationService;

/**
 * 考试报名Service实现
 */
@Service
public class ExamRegistrationServiceImpl implements IExamRegistrationService
{
    @Autowired
    private ExamRegistrationMapper registrationMapper;

    @Override
    public List<ExamRegistration> selectMyRegistrationList(Long userId)
    {
        return registrationMapper.selectMyRegistrationList(userId);
    }

    @Override
    public int insertExamRegistration(ExamRegistration registration)
    {
        return registrationMapper.insertExamRegistration(registration);
    }

    @Override
    public int updateExamRegistration(ExamRegistration registration)
    {
        return registrationMapper.updateExamRegistration(registration);
    }

    @Override
    public int deleteExamRegistrationById(Long registrationId)
    {
        return registrationMapper.deleteExamRegistrationById(registrationId);
    }
}
