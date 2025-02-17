package org.fludland.userservice.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "followers_id_gen")
    @SequenceGenerator(name = "followers_id_gen", sequenceName = "followers_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfile user;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private UserProfile follower;

    @Column(name = "added_on")
    private LocalDateTime addedOn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public UserProfile getFollower() {
        return follower;
    }

    public void setFollower(UserProfile follower) {
        this.follower = follower;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
