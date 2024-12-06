package com.musex.Controllers;

import com.musex.Repository.UserRepository;

import com.musex.entities.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/friends")
public class FriendshipController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{id}/{friendId}")
    public User addFriend(@PathVariable Long id, @PathVariable Long friendId){
        User user = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        User friend = userRepository.findById(friendId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        user.addFriend(friend);
        friend.addFriend(user);
        userRepository.save(friend);
        userRepository.save(user);
        return user;

    }

    @DeleteMapping("/{id}/{friendId}")
    public User removeFriend(@PathVariable Long id, @PathVariable Long friendId){
        User user = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        User friend = userRepository.findById(friendId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found."));
        user.deleteFriend(friend);
        friend.deleteFriend(user);
        userRepository.save(friend);
        userRepository.save(user);
        return  user;

    }
}
