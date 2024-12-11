package com.musex.Controllers;

import com.musex.Repository.UserRepository;
import com.musex.entities.User.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;



@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //Edit user
    @PutMapping("/{id}")
    public User editUser(@PathVariable Long id, @RequestBody User user){
        User userToEdit = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND"));

        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals(authenticatedUser.getUsername(), userToEdit.getUsername()) &&
                authenticatedUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to edit this account");
        }


        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        if (!Objects.equals(user.getUsername(), "")) {
            userToEdit.setUsername(user.getUsername());
        }
        if (!Objects.equals(user.getEmail(), "")) {
            userToEdit.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userToEdit.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(userToEdit);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
