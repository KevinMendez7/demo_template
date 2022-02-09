package com.example.template.demo_template.util.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SignInRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
   
}