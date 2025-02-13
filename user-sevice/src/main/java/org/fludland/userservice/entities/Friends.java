package org.fludland.userservice.entities;

import jakarta.persistence.*;
import org.fludland.userservice.enums.FriendshipStatus;

import java.time.LocalDateTime;

@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friends_id_gen")
    @SequenceGenerator(name = "friends_id_gen", sequenceName = "friends_id_seq", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    private long userid;

    @Column(nullable = false)
    private long friendId;

    @Column
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
