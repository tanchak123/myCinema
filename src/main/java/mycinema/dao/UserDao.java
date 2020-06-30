package mycinema.dao;

import mycinema.model.User;

public interface UserDao {

    User add(User user);

    User findByEmail(String email);
}
