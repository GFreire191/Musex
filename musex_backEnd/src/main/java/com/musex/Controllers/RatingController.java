package com.musex.Controllers;

import com.musex.Repository.MusicRepository;
import com.musex.Repository.RatingRepository;
import com.musex.Repository.UserRepository;
import com.musex.entities.Music;
import com.musex.entities.Rating;
import com.musex.entities.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("api/ratings")
public class RatingController {
    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MusicRepository musicRepository;

    @PostMapping
    public Rating createRating(@RequestParam Long userId, @RequestParam Long musicId,@RequestParam Integer new_rating){
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND"));
        Music music = musicRepository.findById(musicId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"MUSIC NOT FOUND"));
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setMusic(music);
        rating.setRating(new_rating);
        return rating;

    }

    @GetMapping("/music/{musicId}")
    public Set<Rating> getMusicRatings(@PathVariable Long musicId){
        Music music = musicRepository.findById(musicId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"MUSIC NOT FOUND"));
         return music.getRatings();
    }

    @GetMapping("/user/{userId}")
    public Set<Rating> getUserRatings(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND"));
        return user.getRatings();
    }

    //Find rating of a specific user for a specific music rating
    @GetMapping("/user/{userId}/music/{musicId}")
    public Rating getUserMusicRating(@PathVariable Long userId, @PathVariable Long musicId){
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND"));
        Music music = musicRepository.findById(musicId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"MUSIC NOT FOUND"));
        return ratingRepository.findByUserAndMusic(user,music).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"RATING NOT FOUND"));
    }

    //Edit rating of a specific user for a specific music rating
    @PutMapping("/user/{userId}/music/{musicId}")
    public Rating editUserMusicRating(@PathVariable Long userId, @PathVariable Long musicId, @RequestParam Integer new_rating){
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND"));
        Music music = musicRepository.findById(musicId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"MUSIC NOT FOUND"));
        Rating rating = ratingRepository.findByUserAndMusic(user,music).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"RATING NOT FOUND"));
        rating.setRating(new_rating);
        return rating;
    }

    //Delete rating of a specific user for a specific music rating
    @DeleteMapping("/user/{userId}/music/{musicId}")
    public void deleteUserMusicRating(@PathVariable Long userId, @PathVariable Long musicId){
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND"));
        Music music = musicRepository.findById(musicId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"MUSIC NOT FOUND"));
        Rating rating = ratingRepository.findByUserAndMusic(user,music).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"RATING NOT FOUND"));
        ratingRepository.delete(rating);
    }



}
