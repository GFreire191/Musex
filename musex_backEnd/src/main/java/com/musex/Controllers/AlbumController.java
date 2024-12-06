package com.musex.Controllers;


import com.musex.Repository.AlbumRepository;
import com.musex.Repository.MusicRepository;
import com.musex.entities.Album;
import com.musex.entities.Music;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Set;

@RestController
@RequestMapping("api/album")
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    MusicRepository musicRepository;

    @PostMapping
    public Album createAlbum(@Valid @RequestBody Album album){
        return albumRepository.save(album);
    }

    @GetMapping("/{albumId}")
    public Album getAlbum(@PathVariable Long albumId){
        Album album = albumRepository.findById(albumId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ALBUM NOT FOUND"));
        return album;
    }

    @GetMapping("/{albumId}/musics")
    public Set<Music> getMusicsByAlbumId(@PathVariable Long albumId){
        Album album = albumRepository.findById(albumId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ALBUM NOT FOUND"));
        return musicRepository.findByAlbum(album);
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable Long id, @Valid @RequestBody Album album){
        Album albumToEdit =albumRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Album Not Found"));
        albumToEdit.setName(album.getName());
        albumToEdit.setYear(albumToEdit.getYear());
        albumToEdit.setMusics(album.getMusics());
        return albumRepository.save(albumToEdit);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id){
        albumRepository.deleteById(id);
    }
}
