package com.drip.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文章信息表
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 主键，自增 ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 用户 ID
     */
    private Integer userId;

    /**
     * 类型：1--帖子，2--公告
     */
    private String type;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 收藏数
     */
    private Integer collectCount;

    /**
     * 专题 ID
     */
    private Integer specialId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Article other = (Article) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
            && (this.getLikeCount() == null ? other.getLikeCount() == null : this.getLikeCount().equals(other.getLikeCount()))
            && (this.getReadCount() == null ? other.getReadCount() == null : this.getReadCount().equals(other.getReadCount()))
            && (this.getCollectCount() == null ? other.getCollectCount() == null : this.getCollectCount().equals(other.getCollectCount()))
            && (this.getSpecialId() == null ? other.getSpecialId() == null : this.getSpecialId().equals(other.getSpecialId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getLikeCount() == null) ? 0 : getLikeCount().hashCode());
        result = prime * result + ((getReadCount() == null) ? 0 : getReadCount().hashCode());
        result = prime * result + ((getCollectCount() == null) ? 0 : getCollectCount().hashCode());
        result = prime * result + ((getSpecialId() == null) ? 0 : getSpecialId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", readCount=").append(readCount);
        sb.append(", collectCount=").append(collectCount);
        sb.append(", specialId=").append(specialId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}