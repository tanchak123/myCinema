package mycinema.service;

import mycinema.model.User;

public interface UserService {

    User add(User user);

    User findByEmail(String email);
}
