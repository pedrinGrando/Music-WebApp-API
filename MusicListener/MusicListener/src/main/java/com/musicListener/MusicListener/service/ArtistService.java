package com.musicListener.MusicListener.service;

import com.musicListener.MusicListener.model.Artist;
import com.musicListener.MusicListener.repository.ArtistRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    public Artist updateArtist(Long id, Artist updatedArtist) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            Artist existingArtist = optionalArtist.get();
            existingArtist.setName(updatedArtist.getName());
            existingArtist.setProfileImage(updatedArtist.getProfileImage());
            return artistRepository.save(existingArtist);
        } else {
            return null;
        }
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    public Long getArtistIdByName(String name) {
        Artist artist = artistRepository.findByName(name);
        // return artist.getId();
    }
}
