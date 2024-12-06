package com.musex.Controllers;


import com.musex.Repository.ArtistRepository;
import com.musex.Repository.MusicRepository;
import com.musex.entities.Artist;
import com.musex.entities.Music;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MusicRepository musicRepository;

     @PostMapping
    public Artist createArtist(@Valid @RequestBody Artist artist){
         return artistRepository.save(artist);
     }

     @GetMapping("/{id}")
    public Optional<Artist> getArtist(@PathVariable Long id){
         return artistRepository.findById(id);
     }

     @GetMapping("/{id}/musics")
    public Set<Music> getMusics(@PathVariable Long id){
         Artist artist = artistRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Artist Not Found"));
         return artist.getArtistMusics();
     }

     @PutMapping("/{id}")
     public Artist updateArtist(@PathVariable Long id, @Valid @RequestBody Artist artist){
         Artist artistToEdit = artistRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Artist Not Found"));
         artistToEdit.setArtistMusics(artist.getArtistMusics());
         artistToEdit.setAge(artist.getAge());
         artistToEdit.setName(artist.getName());
         artistToEdit.setDescription(artist.getDescription());
         return artistRepository.save(artistToEdit);
     }

     @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id){
         artistRepository.deleteById(id);
     }
}
