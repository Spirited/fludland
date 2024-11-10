package org.fludland.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Thumb thumb = (Thumb) o;

        return new EqualsBuilder().append(id, thumb.id).append(post, thumb.post).append(userId, thumb.userId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(post).append(userId).toHashCode();
    }
}