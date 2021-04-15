package org.kosiuk.webApp.dto;

import org.springframework.stereotype.Component;

@Component
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    boolean user;
    boolean admin;
    boolean manager;

    public UserDto(Integer id, String username, String email, boolean user, boolean admin, boolean manager) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.user = user;
        this.admin = admin;
        this.manager = manager;
    }

    public UserDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
