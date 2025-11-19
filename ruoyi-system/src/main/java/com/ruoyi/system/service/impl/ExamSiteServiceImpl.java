package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ExamSite;
import com.ruoyi.system.mapper.ExamSiteMapper;
import com.ruoyi.system.service.IExamSiteService;

/**
 * 考点 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class ExamSiteServiceImpl implements IExamSiteService
{
    @Autowired
    private ExamSiteMapper examSiteMapper;

    /**
     * 查询考点列表
     * 
     * @param examSite 考点
     * @return 考点集合
     */
    @Override
    public List<ExamSite> selectExamSiteList(ExamSite examSite)
    {
        return examSiteMapper.selectExamSiteList(examSite);
    }

    /**
     * 查询所有考点
     * 
     * @return 考点列表
     */
    @Override
    public List<ExamSite> selectExamSiteAll()
    {
        return examSiteMapper.selectExamSiteAll();
    }

    /**
     * 通过考点ID查询考点
     * 
     * @param siteId 考点ID
     * @return 考点对象
     */
    @Override
    public ExamSite selectExamSiteById(Long siteId)
    {
        return examSiteMapper.selectExamSiteById(siteId);
    }

    /**
     * 新增考点
     * 
     * @param examSite 考点
     * @return 结果
     */
    @Override
    public int insertExamSite(ExamSite examSite)
    {
        return examSiteMapper.insertExamSite(examSite);
    }

    /**
     * 修改考点
     * 
     * @param examSite 考点
     * @return 结果
     */
    @Override
    public int updateExamSite(ExamSite examSite)
    {
        return examSiteMapper.updateExamSite(examSite);
    }

    /**
     * 批量删除考点
     * 
     * @param siteIds 需要删除的考点ID
     * @return 结果
     */
    @Override
    public int deleteExamSiteByIds(Long[] siteIds)
    {
        return examSiteMapper.deleteExamSiteByIds(siteIds);
    }

    /**
     * 删除考点信息
     * 
     * @param siteId 考点ID
     * @return 结果
     */
    @Override
    public int deleteExamSiteById(Long siteId)
    {
        return examSiteMapper.deleteExamSiteById(siteId);
    }

    /**
     * 校验考点名称是否唯一
     * 
     * @param examSite 考点
     * @return 结果
     */
    @Override
    public boolean checkSiteNameUnique(ExamSite examSite)
    {
        Long siteId = StringUtils.isNull(examSite.getSiteId()) ? -1L : examSite.getSiteId();
        ExamSite info = examSiteMapper.checkSiteNameUnique(examSite.getSiteName());
        if (StringUtils.isNotNull(info) && info.getSiteId().longValue() != siteId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验考点编码是否唯一
     * 
     * @param examSite 考点
     * @return 结果
     */
    @Override
    public boolean checkSiteCodeUnique(ExamSite examSite)
    {
        Long siteId = StringUtils.isNull(examSite.getSiteId()) ? -1L : examSite.getSiteId();
        ExamSite info = examSiteMapper.checkSiteCodeUnique(examSite.getSiteCode());
        if (StringUtils.isNotNull(info) && info.getSiteId().longValue() != siteId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
