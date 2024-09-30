package org.fludland.repositories;

import org.fludland.entities.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbRepository extends JpaRepository<Thumb, Long> {
}
