package com.movie.app.service;

import com.movie.app.model.Movie;
import com.movie.app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public void saveMovie(Movie movie) {
        this.movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(long id) {
        Optional<Movie> optional = movieRepository.findById(id);
        Movie movie =null;
        if(optional.isPresent()){
            movie = optional.get();
        }
        else{
            throw new RuntimeException("Employee not found for id");
        }
        return movie;
     }

    @Override
    public void deleteMovieById(long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if(keyword !=null) {
            return this.movieRepository.findAll(pageable, keyword);
        }
        return movieRepository.findAll(pageable);
    }

}
