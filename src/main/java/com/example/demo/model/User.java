package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private Timestamp registrationDate;
    @Column
    private Timestamp lastLoginAttempt;
    @Column
    private String userEmail;
    @Column
    private int failedLoginAttempts;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Timestamp getLastLoginAttempt() {
        return lastLoginAttempt;
    }

    public void setLastLoginAttempt(Timestamp lastLoginAttempt) {
        this.lastLoginAttempt = lastLoginAttempt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", lastLoginAttempt=" + lastLoginAttempt +
                ", userEmail='" + userEmail + '\'' +
                ", failedLoginAttempts=" + failedLoginAttempts +
                '}';
    }
}
