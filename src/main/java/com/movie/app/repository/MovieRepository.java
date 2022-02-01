package com.movie.app.repository;

import com.movie.app.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

    @Query("SELECT m FROM Movie m WHERE CONCAT(m.name, ' ', m.actor, ' ', m.catogery, ' ', m.year ) LIKE %?1%")
    Page<Movie> findAll(Pageable pageable, String keyword);
}