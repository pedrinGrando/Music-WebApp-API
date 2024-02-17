package com.musicListener.MusicListener.repository;

import com.musicListener.MusicListener.model.Music;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findByArtistId(Long artistId);
    Music findByNameMusic(String name);
}
