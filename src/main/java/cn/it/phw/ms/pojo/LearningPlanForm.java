package cn.it.phw.ms.pojo;

import java.util.Date;

public class LearningPlanForm extends BaseEntity {
    private Integer id;

    private Integer ownerId;

    private String onwerName;

    private Integer status;

    private Integer isShare;

    private Date createTime;

    private String approveContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOnwerName() {
        return onwerName;
    }

    public void setOnwerName(String onwerName) {
        this.onwerName = onwerName == null ? null : onwerName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent == null ? null : approveContent.trim();
    }
}