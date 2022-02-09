package com.example.template.demo_template.util.security;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.template.demo_template.model.User;
import com.example.template.demo_template.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService _userService;
    


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
    	try {
    		    		
    		
    		UserPrincipal userPrincipal = null;
	    	
	    	Optional<User> user = _userService.findByEmail(username);

	    	if(!Objects.isNull(user)) {
	    		    		    		
	    		userPrincipal = UserPrincipal.create(user.get());
	    		
	    	}	    	
	    	
	    	return userPrincipal;
			
		} catch (Exception e) {
		
			throw new Error("ERROR " + e.getMessage());
		}        
               
    }

    @Transactional
    public UserDetails loadUserById(Integer id) throws Exception {    	    	
    	try {
    		Optional<User> user = _userService.findByUserId(id.longValue());
            return UserPrincipal.create(user.get());
    	} catch(Exception e) {
    		return null;
    	}
    	
    }
}