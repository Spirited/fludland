package org.fludland.repositories;

import org.fludland.entities.Post;
import org.fludland.entities.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbRepository extends JpaRepository<Thumb, Long> {
    @Query("select count(t.id) from Thumb t where t.post = :post")
    long countAllByPost(Post post);
}
