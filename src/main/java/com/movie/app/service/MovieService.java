package com.movie.app.service;

import com.movie.app.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
  //  List<Movie> search(String keyword);
    List<Movie> getAllMovies();
    void saveMovie(Movie movie);
    Movie getMovieById(long id);
    void deleteMovieById(long id);
    Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
