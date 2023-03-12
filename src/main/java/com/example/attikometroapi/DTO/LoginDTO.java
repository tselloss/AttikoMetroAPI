package com.example.attikometroapi.DTO;


public class LoginDTO {
    private String username;
    private String password;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    private Integer user_id;
    public LoginDTO() {
    }

    public LoginDTO(String username, String password, Integer user_id) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

