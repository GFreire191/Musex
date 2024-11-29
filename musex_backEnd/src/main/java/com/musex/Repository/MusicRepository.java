package com.musex.Repository;

import com.musex.entities.Album;
import com.musex.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MusicRepository extends JpaRepository<Music,Long> {

    Set<Music> findByAlbum(Album album);
}
