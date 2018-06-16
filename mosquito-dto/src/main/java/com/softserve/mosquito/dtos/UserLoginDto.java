package com.softserve.mosquito.dtos;

import javax.validation.constraints.NotBlank;

public class UserLoginDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public UserLoginDto() {
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
}
