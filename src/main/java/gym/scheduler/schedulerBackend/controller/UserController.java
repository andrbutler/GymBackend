/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.User;
import gym.scheduler.schedulerBackend.model.repository.UserRepository;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import gym.scheduler.schedulerBackend.security.CurrentUser;
import gym.scheduler.schedulerBackend.security.JwtTokenProvider;
import gym.scheduler.schedulerBackend.security.UserPrincipal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author andrb
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<User> retrieveAllUsers() {
	return userRepository.findAll();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public User retrieveById(@PathVariable(name = "id") int id) {
        return userRepository.findByUserId(id);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/current")
    public User retrieveCurrentUser(@CurrentUser UserPrincipal u) {
        return userRepository.findByUserId(u.getUserId());
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable(name = "id") int id, @RequestBody User request) {

        User user = userRepository.findByUserId(id);
        if((userRepository.findByUsername(request.getUsername()).isPresent()) 
                && !(request.getUsername().equals(user.getUsername()))) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if((userRepository.findByEmail(request.getEmail()).isPresent()) 
            && !(request.getEmail().equals(user.getEmail()))) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        user.setFName(request.getFName());
        user.setEmail(request.getEmail());
        user.setEnabled(request.getEnabled());
        user.setHeight(request.getHeight());
        user.setLName(request.getLName());
        user.setPhone(request.getPhone());
        user.setStatus(request.getStatus());
        user.setUsername(request.getUsername());

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User updated successfully"));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @PostMapping("/update/pass/{id}")
    public ResponseEntity<?> UpdatePassword(@PathVariable(name = "id") int id, @RequestBody User request) {

        User user = userRepository.findByUserId(id);
        
        
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User updated successfully"));
    }
}

