package mycinema.service.serviceimpl;

import mycinema.dao.UserDao;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.User;
import mycinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
