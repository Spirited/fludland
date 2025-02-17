package org.fludland.userservice.repository;

import org.fludland.userservice.entities.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Followers, Long> {
}
