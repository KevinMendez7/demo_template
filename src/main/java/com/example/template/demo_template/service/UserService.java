package com.example.template.demo_template.service;

import java.util.Optional;

import com.example.template.demo_template.model.User;
import com.example.template.demo_template.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<User> findByUsernameOrEmail(String username, String email) {
    return userRepository.findByUsernameOrEmail(username, email);
  }

  public Optional<User> findByUserId(Long userId) {
    return userRepository.findById(userId);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public Boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }
  
}
