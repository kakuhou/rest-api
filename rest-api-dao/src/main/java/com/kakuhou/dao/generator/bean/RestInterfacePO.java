package com.kakuhou.dao.generator.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口列表
 *
 * @author guopeng
 * @date 2018-05-07
 *
 */
public class RestInterfacePO implements Serializable {
    /** 主键 */
    private String id;

    /** 接口地址 */
    private String interfaceUri;

    /** 是否加密 */
    private Boolean encrypted;

    /** 权限id */
    private String rightId;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInterfaceUri() {
        return interfaceUri;
    }

    public void setInterfaceUri(String interfaceUri) {
        this.interfaceUri = interfaceUri == null ? null : interfaceUri.trim();
    }

    public Boolean getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId == null ? null : rightId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", interfaceUri=").append(interfaceUri);
        sb.append(", encrypted=").append(encrypted);
        sb.append(", rightId=").append(rightId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}