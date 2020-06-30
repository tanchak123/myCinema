package mycinema.service.serviceimpl;

import java.util.List;
import mycinema.dao.MovieDao;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.Movie;
import mycinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    MovieDao md;

    @Override
    public Movie add(Movie movie) {
        return md.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return md.getAll();
    }
}
