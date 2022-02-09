package com.example.template.demo_template.util.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SignUpRequest {
    
  @NotBlank
  @Email
  private String email;

  @NotBlank    
  @Size(min = 6, max = 20)
  private String password;  
}
