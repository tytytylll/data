package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ExamSite;

/**
 * 考点 数据层
 * 
 * @author ruoyi
 */
public interface ExamSiteMapper
{
    /**
     * 查询考点列表
     * 
     * @param examSite 考点
     * @return 考点集合
     */
    public List<ExamSite> selectExamSiteList(ExamSite examSite);

    /**
     * 查询所有考点
     * 
     * @return 考点列表
     */
    public List<ExamSite> selectExamSiteAll();

    /**
     * 通过考点ID查询考点
     * 
     * @param siteId 考点ID
     * @return 考点对象
     */
    public ExamSite selectExamSiteById(Long siteId);

    /**
     * 新增考点
     * 
     * @param examSite 考点
     * @return 结果
     */
    public int insertExamSite(ExamSite examSite);

    /**
     * 修改考点
     * 
     * @param examSite 考点
     * @return 结果
     */
    public int updateExamSite(ExamSite examSite);

    /**
     * 删除考点
     * 
     * @param siteId 考点ID
     * @return 结果
     */
    public int deleteExamSiteById(Long siteId);

    /**
     * 批量删除考点
     * 
     * @param siteIds 需要删除的考点ID
     * @return 结果
     */
    public int deleteExamSiteByIds(Long[] siteIds);

    /**
     * 校验考点名称是否唯一
     * 
     * @param siteName 考点名称
     * @return 结果
     */
    public ExamSite checkSiteNameUnique(String siteName);

    /**
     * 校验考点编码是否唯一
     * 
     * @param siteCode 考点编码
     * @return 结果
     */
    public ExamSite checkSiteCodeUnique(String siteCode);
}
