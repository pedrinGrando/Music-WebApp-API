package com.musicListener.MusicListener.repository;

import com.musicListener.MusicListener.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);
}
