package com.bug.bugiss2.domain;

import java.io.Serializable;

/**
 * Define an User type entity
 */
public class User implements Serializable {
    private String email;
    private String password;
    private String role;
    private Long id;
    private static final long serialVersionUID = 7331115341259248461L;
    public User( String email, String password,String role) {
        this.email = email;
        this.password = password;
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}