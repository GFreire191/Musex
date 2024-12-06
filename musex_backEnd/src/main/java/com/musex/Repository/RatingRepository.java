package com.musex.Repository;

import com.musex.entities.Music;
import com.musex.entities.Rating;
import com.musex.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    Optional<Rating> findByUserAndMusic(User user, Music music);


}
