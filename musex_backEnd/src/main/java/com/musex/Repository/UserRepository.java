package com.musex.Repository;
import com.musex.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);
    public UserDetails findByUsername(String username);



}
