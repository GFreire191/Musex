package com.musex.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;
    @NotEmpty
    @Column(nullable = false)
    private Integer age;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "artist_music",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    @JsonBackReference
    Set<Music> artistMusics = new HashSet<>();

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Music> getArtistMusics() {
        return artistMusics;
    }

}
