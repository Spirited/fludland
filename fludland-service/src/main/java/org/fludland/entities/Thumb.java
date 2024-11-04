package org.fludland.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "thumbs", schema = "fludland")
public class Thumb {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thumbs_id_gen")
    @SequenceGenerator(name = "thumbs_id_gen", sequenceName = "thumbs_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Post post;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}