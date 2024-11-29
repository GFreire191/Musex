package com.musex.Controllers;

import com.musex.Repository.UserRepository;
import com.musex.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;



@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @PostMapping("/{id}/friends/{friendId}")
    public User addFriend(@PathVariable Long id, @PathVariable Long friendId){
        User user = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        User friend = userRepository.findById(friendId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        user.addFriend(friend);
        friend.addFriend(user);
        userRepository.save(friend);
        return userRepository.save(user);

    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public User removeFriend(@PathVariable Long id, @PathVariable Long friendId){
        User user = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        User friend = userRepository.findById(friendId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        user.deleteFriend(friend);
        friend.deleteFriend(user);
        userRepository.save(friend);
        return userRepository.save(user);

    }
}
