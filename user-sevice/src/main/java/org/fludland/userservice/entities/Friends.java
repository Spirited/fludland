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

    @Column
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfile user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private UserProfile friend;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserProfile getFriend() {
        return friend;
    }

    public void setFriend(UserProfile profile) {
        this.friend = profile;
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

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
