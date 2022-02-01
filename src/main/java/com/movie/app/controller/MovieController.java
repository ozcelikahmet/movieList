package com.movie.app.controller;
import com.movie.app.model.Movie;
import com.movie.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public  String viewHomePage(Model model) {
        String keyword=null;
        return findPaginated(1, "year", "asc", keyword, model);
    }


    @GetMapping("/showNewMovieForm")
    public String showNewMovieForm(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "new_movie";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie") Movie movie){
        movieService.saveMovie(movie);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id, Model model){
        Movie movie= movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "update_movie";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable(value = "id") long id){

    this.movieService.deleteMovieById((id));
    return "redirect:/";

    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam(required=false, name="keyword") String keyword,
                                Model model) {
        int pageSize = 5;

        Page<Movie> page = movieService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);
        List<Movie> listMovies = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listMovies", listMovies);
        model.addAttribute("keyword", keyword);
        return "index";
    }


}