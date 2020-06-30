package mycinema.service;

import java.util.List;
import mycinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
