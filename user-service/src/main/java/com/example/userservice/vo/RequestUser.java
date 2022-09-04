package com.example.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class RequestUser {

    @NotNull(message = "Email must not be null.")
    @Size(min = 2, message = "Email must not be less than 2 characters.")
    @Email
    private String email;

    @NotNull(message = "Name must not be null.")
    @Size(min = 2, message = "Name must not be less than 2 characters.")
    private String name;

    @NotNull(message = "Password must not be null.")
    @Size(min = 8, message = "Password not be equal or greater than 8 characters.")
    private String pwd;
}
