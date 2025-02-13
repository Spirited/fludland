package org.fludland.userservice.repository;

import org.fludland.userservice.entities.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendsRepository extends JpaRepository<Friends, Long> {
}
