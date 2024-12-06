package com.musex.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false,unique = true)
    private String name;

    @NotEmpty
    @Column
    private Integer year;

    @OneToMany(mappedBy = "album")
    Set<Music> musics = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public void setMusics(Set<Music> musics) {
        this.musics = musics;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


}
