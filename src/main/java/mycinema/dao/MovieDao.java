package mycinema.dao;

import java.util.List;
import mycinema.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
