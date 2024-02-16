package com.musicListener.MusicListener.controller;

import com.musicListener.MusicListener.model.Music;
import com.musicListener.MusicListener.service.MusicService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    //Salvar
    @PostMapping
    public ResponseEntity<Music> createSong(@RequestBody Music song) {
        if (song.getTitle() == null || song.getDurationMinutes() == 0 || song.getDurationSeconds() == 0 || song.getAlbum() == null || song.getTrackNumber() <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            Music savedSong = musicService.saveSong(song);
            return new ResponseEntity<>(savedSong, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Music> getSongById(@PathVariable Long id) {
        Optional<Music> song = musicService.getSongById(id);
        return song.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Music> updateSong(@PathVariable Long id, @RequestBody Music song) {
        try {
            Optional<Music> existingSong = musicService.getSongById(id);
            if (existingSong.isPresent()) {
                Music updatedSong = musicService.updateSong(id, song);
                return new ResponseEntity<>(updatedSong, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSong(@PathVariable Long id) {
        try {
            boolean isDeleted = musicService.deleteSong(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Por Artista
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<Music>> getSongsByArtistIdWithAlbumInfo(@PathVariable Long artistId) {
        List<Music> songs = musicService.getSongsByArtistIdWithAlbumInfo(artistId);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}

