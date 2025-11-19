package com.ruoyi.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考点表 exam_site
 * 
 * @author ruoyi
 */
public class ExamSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考点ID */
    @Excel(name = "考点ID", cellType = ColumnType.NUMERIC)
    private Long siteId;

    /** 考点名称 */
    @Excel(name = "考点名称")
    private String siteName;

    /** 考点编码 */
    @Excel(name = "考点编码")
    private String siteCode;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 容纳人数 */
    @Excel(name = "容纳人数")
    private Integer capacity;

    /** 状态（0启用 1禁用） */
    @Excel(name = "状态", readConverterExp = "0=启用,1=禁用")
    private String status;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer sortOrder;

    public Long getSiteId()
    {
        return siteId;
    }

    public void setSiteId(Long siteId)
    {
        this.siteId = siteId;
    }

    @NotBlank(message = "考点名称不能为空")
    @Size(min = 0, max = 100, message = "考点名称长度不能超过100个字符")
    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    @NotBlank(message = "考点编码不能为空")
    @Size(min = 0, max = 50, message = "考点编码长度不能超过50个字符")
    public String getSiteCode()
    {
        return siteCode;
    }

    public void setSiteCode(String siteCode)
    {
        this.siteCode = siteCode;
    }

    @Size(min = 0, max = 50, message = "省份长度不能超过50个字符")
    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    @Size(min = 0, max = 50, message = "城市长度不能超过50个字符")
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Size(min = 0, max = 50, message = "区县长度不能超过50个字符")
    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    @Size(min = 0, max = 200, message = "详细地址长度不能超过200个字符")
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Size(min = 0, max = 50, message = "联系人长度不能超过50个字符")
    public String getContactPerson()
    {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    @Size(min = 0, max = 20, message = "联系电话长度不能超过20个字符")
    public String getContactPhone()
    {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    public Integer getCapacity()
    {
        return capacity;
    }

    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
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
            .append("siteId", getSiteId())
            .append("siteName", getSiteName())
            .append("siteCode", getSiteCode())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("address", getAddress())
            .append("contactPerson", getContactPerson())
            .append("contactPhone", getContactPhone())
            .append("capacity", getCapacity())
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
