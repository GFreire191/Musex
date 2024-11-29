package com.musex.Controllers;

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
 @RequestMapping("api/musics")
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    @PostMapping
    public Music createMusic(@Valid @RequestBody Music music){
       return musicRepository.save(music);
    }

    @GetMapping("/{id}")
    public Optional<Music> getMusic(@PathVariable Long id){
        return musicRepository.findById(id);
    }

    @GetMapping("{music_id}/artists")
    public Set<Artist> getArtistByMusic(@PathVariable Long music_id){
        Music music = musicRepository.findById(music_id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Music Not Found"));
        return music.getMusicArtists();
    }

}
