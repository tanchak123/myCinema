package mycinema.service.serviceimpl;

import java.util.List;
import mycinema.dao.MovieSessionDao;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.MovieSession;
import mycinema.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    MovieSessionDao movieSessionDao;

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> getAll() {
        return movieSessionDao.getAll();
    }
}
