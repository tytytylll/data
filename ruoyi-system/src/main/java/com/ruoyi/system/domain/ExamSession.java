package com.ruoyi.system.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试场次表 exam_session
 * 
 * @author ruoyi
 */
public class ExamSession extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场次ID */
    @Excel(name = "场次ID", cellType = ColumnType.NUMERIC)
    private Long sessionId;

    /** 科目ID */
    @Excel(name = "科目ID")
    private Long subjectId;

    /** 科目名称 */
    @Excel(name = "科目名称")
    private String subjectName;

    /** 考点ID */
    @Excel(name = "考点ID")
    private Long siteId;

    /** 考点名称 */
    @Excel(name = "考点名称")
    private String siteName;

    /** 场次名称 */
    @Excel(name = "场次名称")
    private String sessionName;

    /** 场次编码 */
    @Excel(name = "场次编码")
    private String sessionCode;

    /** 考试日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date examDate;

    /** 开始时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "HH:mm:ss")
    private Date endTime;

    /** 最大考生数 */
    @Excel(name = "最大考生数")
    private Integer maxCandidates;

    /** 当前报名人数 */
    @Excel(name = "当前报名人数")
    private Integer currentCandidates;

    /** 状态（0待开始 1进行中 2已结束 3已取消） */
    @Excel(name = "状态", readConverterExp = "0=待开始,1=进行中,2=已结束,3=已取消")
    private String status;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer sortOrder;

    public Long getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(Long sessionId)
    {
        this.sessionId = sessionId;
    }

    @NotNull(message = "科目ID不能为空")
    public Long getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(Long subjectId)
    {
        this.subjectId = subjectId;
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    @NotNull(message = "考点ID不能为空")
    public Long getSiteId()
    {
        return siteId;
    }

    public void setSiteId(Long siteId)
    {
        this.siteId = siteId;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    @NotBlank(message = "场次名称不能为空")
    @Size(min = 0, max = 100, message = "场次名称长度不能超过100个字符")
    public String getSessionName()
    {
        return sessionName;
    }

    public void setSessionName(String sessionName)
    {
        this.sessionName = sessionName;
    }

    @NotBlank(message = "场次编码不能为空")
    @Size(min = 0, max = 50, message = "场次编码长度不能超过50个字符")
    public String getSessionCode()
    {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode)
    {
        this.sessionCode = sessionCode;
    }

    @NotNull(message = "考试日期不能为空")
    public Date getExamDate()
    {
        return examDate;
    }

    public void setExamDate(Date examDate)
    {
        this.examDate = examDate;
    }

    @NotNull(message = "开始时间不能为空")
    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    @NotNull(message = "结束时间不能为空")
    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Integer getMaxCandidates()
    {
        return maxCandidates;
    }

    public void setMaxCandidates(Integer maxCandidates)
    {
        this.maxCandidates = maxCandidates;
    }

    public Integer getCurrentCandidates()
    {
        return currentCandidates;
    }

    public void setCurrentCandidates(Integer currentCandidates)
    {
        this.currentCandidates = currentCandidates;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sessionId", getSessionId())
            .append("subjectId", getSubjectId())
            .append("subjectName", getSubjectName())
            .append("siteId", getSiteId())
            .append("siteName", getSiteName())
            .append("sessionName", getSessionName())
            .append("sessionCode", getSessionCode())
            .append("examDate", getExamDate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("maxCandidates", getMaxCandidates())
            .append("currentCandidates", getCurrentCandidates())
            .append("status", getStatus())
            .append("sortOrder", getSortOrder())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
