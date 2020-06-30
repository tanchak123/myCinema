package mycinema.dao;

import java.util.List;
import mycinema.model.MovieSession;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> getAll();
}
