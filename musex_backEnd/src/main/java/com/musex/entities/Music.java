package com.musex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String title;
    @NotEmpty
    @Column(nullable = false)
    private Integer duration;

    @ManyToMany(mappedBy = "artistMusics")
    @JsonManagedReference
    Set<Artist> musicArtists = new HashSet<>();

    @OneToMany(mappedBy = "music")
    @JsonIgnore
    Set<Rating> ratings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    Album album;

    public Long getId() {
        return id;
    }

    public Integer getDuration() {
        return duration;
    }

    public Album getAlbum() {
        return album;
    }

    public String getTitle() {
        return title;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Artist> getMusicArtists() {
        return musicArtists;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

}
