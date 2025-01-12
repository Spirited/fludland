package org.fludland.userservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userprofile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userprofile_id_gen")
    @SequenceGenerator(name = "userprofile_id_gen", sequenceName = "userprofile_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "logoid")
    private Long logoImageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLogoImageId() {
        return logoImageId;
    }

    public void setLogoImageId(Long logoImageId) {
        this.logoImageId = logoImageId;
    }
}
