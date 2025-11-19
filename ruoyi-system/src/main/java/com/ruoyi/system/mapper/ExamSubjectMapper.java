package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ExamSubject;

/**
 * 考试科目 数据层
 * 
 * @author ruoyi
 */
public interface ExamSubjectMapper
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
     * 删除考试科目
     * 
     * @param subjectId 科目ID
     * @return 结果
     */
    public int deleteExamSubjectById(Long subjectId);

    /**
     * 批量删除考试科目
     * 
     * @param subjectIds 需要删除的科目ID
     * @return 结果
     */
    public int deleteExamSubjectByIds(Long[] subjectIds);

    /**
     * 校验科目名称是否唯一
     * 
     * @param subjectName 科目名称
     * @return 结果
     */
    public ExamSubject checkSubjectNameUnique(String subjectName);

    /**
     * 校验科目编码是否唯一
     * 
     * @param subjectCode 科目编码
     * @return 结果
     */
    public ExamSubject checkSubjectCodeUnique(String subjectCode);
}
