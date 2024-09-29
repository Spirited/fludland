package org.fludland.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

//@Entity
//@Table(name = "thumbs", schema = "fludland")
public class Thumb {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thumbs_id_gen")
    @SequenceGenerator(name = "thumbs_id_gen", sequenceName = "thumbs_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}