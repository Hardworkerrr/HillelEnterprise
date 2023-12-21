package com.hillel.javaee.validation.entity;

import com.hillel.javaee.validation.annotation.PasswordConstraint;
import jakarta.validation.constraints.*;

public class AuthenticationEntity {

    @Pattern(regexp = "[a-zA-Z0-9]+$", message = "Only letters and digits without spaces !")
    @Size(min = 6, max = 16, message = "Minimum size - 6, Maximus size - 16")
    private String username;
    @PasswordConstraint(min = 6, max = 24, regexp = "[^ /^%'?#<>@]+")
    private String password;
    @Pattern(regexp ="^(?:\\d{10}|\\w+@\\w+\\.\\w{2,3})$", message = "Unknown number format !")
    private String phoneNumber;
    @Pattern(regexp = "^(?:\\d{10}|\\w+@\\w+\\w+\\.\\w{2,3})$", message = "Unknown email format !")
    private String email;
    @Pattern(regexp ="^([A-Za-z]+)*( [A-Za-z]+)", message = "Please type valid full name with space ! ")
    private String fullName;

    public AuthenticationEntity() {

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
