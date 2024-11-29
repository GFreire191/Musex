package com.musex.Controllers;


import com.musex.Repository.ArtistRepository;
import com.musex.entities.Artist;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

     @PostMapping
    public Artist createArtist(@Valid @RequestBody Artist artist){
         return artistRepository.save(artist);
     }

     @GetMapping("/{id}")
    public Optional<Artist> getArtist(@PathVariable Long id){
         return artistRepository.findById(id);
     }
}
