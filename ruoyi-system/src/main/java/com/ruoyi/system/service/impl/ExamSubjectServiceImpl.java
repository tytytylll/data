package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ExamSubject;
import com.ruoyi.system.mapper.ExamSubjectMapper;
import com.ruoyi.system.service.IExamSubjectService;

/**
 * 考试科目 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class ExamSubjectServiceImpl implements IExamSubjectService
{
    @Autowired
    private ExamSubjectMapper examSubjectMapper;

    /**
     * 查询考试科目列表
     * 
     * @param examSubject 考试科目
     * @return 考试科目集合
     */
    @Override
    public List<ExamSubject> selectExamSubjectList(ExamSubject examSubject)
    {
        return examSubjectMapper.selectExamSubjectList(examSubject);
    }

    /**
     * 查询所有考试科目
     * 
     * @return 考试科目列表
     */
    @Override
    public List<ExamSubject> selectExamSubjectAll()
    {
        return examSubjectMapper.selectExamSubjectAll();
    }

    /**
     * 通过科目ID查询考试科目
     * 
     * @param subjectId 科目ID
     * @return 考试科目对象
     */
    @Override
    public ExamSubject selectExamSubjectById(Long subjectId)
    {
        return examSubjectMapper.selectExamSubjectById(subjectId);
    }

    /**
     * 新增考试科目
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    @Override
    public int insertExamSubject(ExamSubject examSubject)
    {
        return examSubjectMapper.insertExamSubject(examSubject);
    }

    /**
     * 修改考试科目
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    @Override
    public int updateExamSubject(ExamSubject examSubject)
    {
        return examSubjectMapper.updateExamSubject(examSubject);
    }

    /**
     * 批量删除考试科目
     * 
     * @param subjectIds 需要删除的科目ID
     * @return 结果
     */
    @Override
    public int deleteExamSubjectByIds(Long[] subjectIds)
    {
        return examSubjectMapper.deleteExamSubjectByIds(subjectIds);
    }

    /**
     * 删除考试科目信息
     * 
     * @param subjectId 科目ID
     * @return 结果
     */
    @Override
    public int deleteExamSubjectById(Long subjectId)
    {
        return examSubjectMapper.deleteExamSubjectById(subjectId);
    }

    /**
     * 校验科目名称是否唯一
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    @Override
    public boolean checkSubjectNameUnique(ExamSubject examSubject)
    {
        Long subjectId = StringUtils.isNull(examSubject.getSubjectId()) ? -1L : examSubject.getSubjectId();
        ExamSubject info = examSubjectMapper.checkSubjectNameUnique(examSubject.getSubjectName());
        if (StringUtils.isNotNull(info) && info.getSubjectId().longValue() != subjectId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验科目编码是否唯一
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    @Override
    public boolean checkSubjectCodeUnique(ExamSubject examSubject)
    {
        Long subjectId = StringUtils.isNull(examSubject.getSubjectId()) ? -1L : examSubject.getSubjectId();
        ExamSubject info = examSubjectMapper.checkSubjectCodeUnique(examSubject.getSubjectCode());
        if (StringUtils.isNotNull(info) && info.getSubjectId().longValue() != subjectId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
