package org.kosiuk.webApp.dto;


import org.springframework.stereotype.Component;

@Component
public class UserCreationDto {
    private String username;
    private String email;
    private String password;
    boolean user;
    boolean admin;
    boolean manager;

    public UserCreationDto(String username, String email, String password, boolean user, boolean admin, boolean manager) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.user = user;
        this.admin = admin;
        this.manager = manager;
    }

    public UserCreationDto() {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
