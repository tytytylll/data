package com.ruoyi.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考生信息表 exam_candidate
 */
public class ExamCandidate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Excel(name = "考生ID")
    private Long candidateId;

    @Excel(name = "考生姓名")
    private String candidateName;

    @Excel(name = "身份证号")
    private String idCard;

    @Excel(name = "手机号码")
    private String phone;

    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String gender;

    @Excel(name = "邮箱")
    private String email;

    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public Long getCandidateId() { return candidateId; }
    public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }

    @NotBlank(message = "考生姓名不能为空")
    @Size(max = 50, message = "考生姓名长度不能超过50个字符")
    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    @Size(max = 18, message = "身份证号长度不能超过18个字符")
    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }

    @Size(max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("candidateId", getCandidateId())
            .append("candidateName", getCandidateName())
            .append("idCard", getIdCard())
            .append("phone", getPhone())
            .append("gender", getGender())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
