package mycinema.service.serviceimpl;

import java.util.List;
import mycinema.dao.CinemaHallDao;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.CinemaHall;
import mycinema.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
