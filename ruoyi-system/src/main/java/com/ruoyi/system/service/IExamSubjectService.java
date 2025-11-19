package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ExamSubject;

/**
 * 考试科目 服务层
 * 
 * @author ruoyi
 */
public interface IExamSubjectService
{
    /**
     * 查询考试科目列表
     * 
     * @param examSubject 考试科目
     * @return 考试科目集合
     */
    public List<ExamSubject> selectExamSubjectList(ExamSubject examSubject);

    /**
     * 查询所有考试科目
     * 
     * @return 考试科目列表
     */
    public List<ExamSubject> selectExamSubjectAll();

    /**
     * 通过科目ID查询考试科目
     * 
     * @param subjectId 科目ID
     * @return 考试科目对象
     */
    public ExamSubject selectExamSubjectById(Long subjectId);

    /**
     * 新增考试科目
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    public int insertExamSubject(ExamSubject examSubject);

    /**
     * 修改考试科目
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    public int updateExamSubject(ExamSubject examSubject);

    /**
     * 批量删除考试科目
     * 
     * @param subjectIds 需要删除的科目ID
     * @return 结果
     */
    public int deleteExamSubjectByIds(Long[] subjectIds);

    /**
     * 删除考试科目信息
     * 
     * @param subjectId 科目ID
     * @return 结果
     */
    public int deleteExamSubjectById(Long subjectId);

    /**
     * 校验科目名称是否唯一
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    public boolean checkSubjectNameUnique(ExamSubject examSubject);

    /**
     * 校验科目编码是否唯一
     * 
     * @param examSubject 考试科目
     * @return 结果
     */
    public boolean checkSubjectCodeUnique(ExamSubject examSubject);
}
