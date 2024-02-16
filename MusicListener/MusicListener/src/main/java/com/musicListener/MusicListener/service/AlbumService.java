package com.musicListener.MusicListener.service;

import com.musicListener.MusicListener.model.Album;
import com.musicListener.MusicListener.repository.AlbumRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    public Album updateAlbum(Long id, Album updatedAlbum) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isPresent()) {
            Album existingAlbum = optionalAlbum.get();
            existingAlbum.setTitle(updatedAlbum.getTitle());
            existingAlbum.setReleaseYear(updatedAlbum.getReleaseYear());
            existingAlbum.setCoverImage(updatedAlbum.getCoverImage());
            return albumRepository.save(existingAlbum);
        } else {
            return null;
        }
    }

    public boolean deleteAlbum(Long id) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isPresent()) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
