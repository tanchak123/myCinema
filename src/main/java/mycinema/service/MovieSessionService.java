package mycinema.service;

import java.util.List;
import mycinema.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> getAll();
}
