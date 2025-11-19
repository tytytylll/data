package com.ruoyi.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试科目表 exam_subject
 * 
 * @author ruoyi
 */
public class ExamSubject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 科目ID */
    @Excel(name = "科目ID", cellType = ColumnType.NUMERIC)
    private Long subjectId;

    /** 科目名称 */
    @Excel(name = "科目名称")
    private String subjectName;

    /** 科目编码 */
    @Excel(name = "科目编码")
    private String subjectCode;

    /** 科目描述 */
    @Excel(name = "科目描述")
    private String description;

    /** 状态（0启用 1停用） */
    @Excel(name = "状态", readConverterExp = "0=启用,1=停用")
    private String status;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer sortOrder;

    public Long getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(Long subjectId)
    {
        this.subjectId = subjectId;
    }

    @NotBlank(message = "科目名称不能为空")
    @Size(min = 0, max = 100, message = "科目名称长度不能超过100个字符")
    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    @NotBlank(message = "科目编码不能为空")
    @Size(min = 0, max = 50, message = "科目编码长度不能超过50个字符")
    public String getSubjectCode()
    {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode)
    {
        this.subjectCode = subjectCode;
    }

    @Size(min = 0, max = 500, message = "科目描述长度不能超过500个字符")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
            .append("subjectId", getSubjectId())
            .append("subjectName", getSubjectName())
            .append("subjectCode", getSubjectCode())
            .append("description", getDescription())
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
