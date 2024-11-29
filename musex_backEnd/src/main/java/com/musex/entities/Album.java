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

}
