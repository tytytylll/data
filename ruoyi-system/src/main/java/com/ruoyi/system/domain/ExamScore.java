package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试成绩表 exam_score
 * 
 * @author ruoyi
 */
public class ExamScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩ID */
    @Excel(name = "成绩ID", cellType = ColumnType.NUMERIC)
    private Long scoreId;

    /** 考生ID */
    @Excel(name = "考生ID", cellType = ColumnType.NUMERIC)
    private Long candidateId;

    /** 用户ID */
    @Excel(name = "用户ID", cellType = ColumnType.NUMERIC)
    private Long userId;

    /** 科目ID */
    @Excel(name = "科目ID", cellType = ColumnType.NUMERIC)
    private Long subjectId;

    /** 考点ID */
    @Excel(name = "考点ID", cellType = ColumnType.NUMERIC)
    private Long siteId;

    /** 场次ID */
    @Excel(name = "场次ID", cellType = ColumnType.NUMERIC)
    private Long sessionId;

    /** 考试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "考试时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date examTime;

    /** 成绩分数 */
    @Excel(name = "成绩分数")
    private BigDecimal score;

    /** 是否通过（0未通过 1通过） */
    @Excel(name = "是否通过", readConverterExp = "0=未通过,1=通过")
    private String passStatus;

    /** 成绩等级 */
    @Excel(name = "成绩等级")
    private String scoreLevel;

    /** 状态（0正常 1作废） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=作废")
    private String status;

    /** 发布状态（0未发布 1已发布） */
    @Excel(name = "发布状态", readConverterExp = "0=未发布,1=已发布")
    private String publishStatus;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /** 定时发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "定时发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date scheduledPublishTime;

    /** 用户名称（非数据库字段） */
    private String userName;

    /** 考生名称（非数据库字段） */
    private String candidateName;

    /** 科目名称（非数据库字段） */
    private String subjectName;

    /** 考点名称（非数据库字段） */
    private String siteName;

    /** 场次名称（非数据库字段） */
    private String sessionName;

    public Long getScoreId() { return scoreId; }
    public void setScoreId(Long scoreId) { this.scoreId = scoreId; }

    public Long getCandidateId() { return candidateId; }
    public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public Long getSiteId() { return siteId; }
    public void setSiteId(Long siteId) { this.siteId = siteId; }

    public Long getSessionId() { return sessionId; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }

    public Date getExamTime() { return examTime; }
    public void setExamTime(Date examTime) { this.examTime = examTime; }

    public BigDecimal getScore() { return score; }
    public void setScore(BigDecimal score) { this.score = score; }

    public String getPassStatus() { return passStatus; }
    public void setPassStatus(String passStatus) { this.passStatus = passStatus; }

    public String getScoreLevel() { return scoreLevel; }
    public void setScoreLevel(String scoreLevel) { this.scoreLevel = scoreLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPublishStatus() { return publishStatus; }
    public void setPublishStatus(String publishStatus) { this.publishStatus = publishStatus; }

    public Date getPublishTime() { return publishTime; }
    public void setPublishTime(Date publishTime) { this.publishTime = publishTime; }

    public Date getScheduledPublishTime() { return scheduledPublishTime; }
    public void setScheduledPublishTime(Date scheduledPublishTime) { this.scheduledPublishTime = scheduledPublishTime; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getSiteName() { return siteName; }
    public void setSiteName(String siteName) { this.siteName = siteName; }

    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("scoreId", getScoreId())
            .append("userId", getUserId())
            .append("subjectId", getSubjectId())
            .append("siteId", getSiteId())
            .append("sessionId", getSessionId())
            .append("examTime", getExamTime())
            .append("score", getScore())
            .append("passStatus", getPassStatus())
            .append("scoreLevel", getScoreLevel())
            .append("status", getStatus())
            .append("publishStatus", getPublishStatus())
            .append("publishTime", getPublishTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
