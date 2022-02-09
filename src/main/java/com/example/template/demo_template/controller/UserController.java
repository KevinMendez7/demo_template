package com.example.template.demo_template.controller;

import java.util.Optional;

import com.example.template.demo_template.model.User;
import com.example.template.demo_template.service.UserService;
import com.example.template.demo_template.util.payload.response.ApiResponse;
import com.example.template.demo_template.util.payload.response.ApiResponseObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserService _userService;

  @GetMapping("/user")
    public ApiResponse checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !_userService.existsByEmail(email);
        return new ApiResponse(true, 200, "Email is available: " + isAvailable);
    }

  @GetMapping("/user/{id}")
    public ApiResponseObject getUsers(@PathVariable("id") Integer userId) {
      Optional<User> _user = _userService.findByUserId(userId.longValue());
      return new ApiResponseObject(true, 200, _user);
    }
  
}
