package com.musex.entities;


import com.musex.entities.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "music_id",nullable = false)
    private Music music;

    @NotEmpty
    @Column(nullable = false)
    private Integer rating;

    public User getUser() {
        return user;
    }

    public Integer getRating() {
        return rating;
    }

    public Music getMusic() {
        return music;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
