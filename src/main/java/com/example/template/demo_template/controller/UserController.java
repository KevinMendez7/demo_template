package com.example.template.demo_template.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import com.example.template.demo_template.model.User;
import com.example.template.demo_template.repository.UserRepository;
import com.example.template.demo_template.service.UserService;
import com.example.template.demo_template.util.payload.request.SignInRequest;
import com.example.template.demo_template.util.payload.request.SignUpRequest;
import com.example.template.demo_template.util.payload.response.ApiResponse;
import com.example.template.demo_template.util.payload.response.ApiResponseObject;
import com.example.template.demo_template.util.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  PasswordEncoder passwordEncoder;    
  
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserService _userService;

  @Autowired
  JwtTokenProvider tokenProvider;   

  @Autowired
  UserRepository userRep;

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

  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@Valid @RequestBody SignInRequest signInRequest) {

    Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		signInRequest.getEmail(),
                		signInRequest.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);  

        String jwt = tokenProvider.generateToken(authentication);        
        return ResponseEntity.ok(new ApiResponse(true, 200, jwt));    		
    
  }

  @PostMapping("/auth/register")
  public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest signUpRequest) {
    
    
    Boolean exists = _userService.existsByEmail(signUpRequest.getEmail());

    if(exists) {
      return new ResponseEntity<ApiResponse>(new ApiResponse(false, 200, "user already exists"), HttpStatus.OK); 
    }

    User user = new User();
    user.setEmail(signUpRequest.getEmail());
    user.setUsername(signUpRequest.getEmail().split("@")[0]);
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setDateCreated(new Date());
    user.setWhoCreated("test_rest_api");
    try {
      userRep.save(user);
      return new ResponseEntity<ApiResponse>(new ApiResponse(true, 200, "user created succesfully"), HttpStatus.CREATED); 
    } catch( Exception ex) {
      System.out.println(ex);
      return new ResponseEntity<ApiResponse>(new ApiResponse(false, 500, "something happened"), HttpStatus.INTERNAL_SERVER_ERROR); 

    }
  }

  @GetMapping("/auth/bad-credentials")
  public ResponseEntity<?> badCredentials() {
    return new ResponseEntity<String>("bad credentials", HttpStatus.UNAUTHORIZED);
  }
}
