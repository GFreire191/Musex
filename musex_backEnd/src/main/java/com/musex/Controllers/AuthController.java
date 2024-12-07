package com.musex.Controllers;

import com.musex.Repository.UserRepository;
import com.musex.entities.User.*;
import com.musex.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.username(),data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        if(this.userRepository.findByUsername(data.username()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPass = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User();
        newUser.setUsername(data.username());
        newUser.setPassword(encryptedPass);
        newUser.setEmail(data.email());
        newUser.setRole(Role.USER);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register-admin")
    public ResponseEntity registerAdmin(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByUsername(data.username())!= null) return ResponseEntity.badRequest().build();
        String ecnryptedPass = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User();
        newUser.setUsername(data.username());
        newUser.setPassword(ecnryptedPass);
        newUser.setEmail(data.email());
        newUser.setRole(Role.ADMIN);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
