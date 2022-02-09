package com.example.template.demo_template.util.payload.response;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ApiResponseObject {
  private Boolean success;
  private int statusCode;
  private Object object;
}
