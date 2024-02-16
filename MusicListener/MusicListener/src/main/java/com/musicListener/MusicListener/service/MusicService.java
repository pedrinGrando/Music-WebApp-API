package com.musicListener.MusicListener.service;

import com.musicListener.MusicListener.model.Music;
import com.musicListener.MusicListener.repository.MusicRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Music saveSong(Music song) {
        return musicRepository.save(song);
    }

    public Optional<Music> getSongById(Long id) {
        return musicRepository.findById(id);
    }

    public Music updateSong(Long id, Music updatedSong) {
        Optional<Music> optionalSong = musicRepository.findById(id);
        if (optionalSong.isPresent()) {
            Music existingSong = optionalSong.get();
            existingSong.setTitle(updatedSong.getTitle());
            existingSong.setDurationMinutes(updatedSong.getDurationMinutes());
            existingSong.setDurationSeconds(updatedSong.getDurationSeconds());
            return musicRepository.save(existingSong);
        } else {
            return null;
        }
    }

    public boolean deleteSong(Long id) {
        Optional<Music> optionalSong = musicRepository.findById(id);
        if (optionalSong.isPresent()) {
            musicRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Music> getSongsByArtistId(Long artistId) {
        return songRepository.findByArtistId(artistId);
    }
}
