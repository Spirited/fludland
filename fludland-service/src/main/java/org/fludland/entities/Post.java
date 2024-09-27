package org.fludland.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.sql.Timestamp;

@Entity
@Table(name = "posts", schema = "fludland")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_gen")
    @SequenceGenerator(name = "posts_id_gen", sequenceName = "posts_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 1000)
    @NotNull
    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "media_file_id")
    private Integer mediaFileId;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at", insertable = false)
    private Timestamp modifiedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMediaFileId() {
        return mediaFileId;
    }

    public void setMediaFileId(Integer mediaFileId) {
        this.mediaFileId = mediaFileId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("content", content)
                .append("userId", userId)
                .append("mediaFileId", mediaFileId)
                .append("createdAt", createdAt)
                .append("modifiedAt", modifiedAt)
                .toString();
    }
}