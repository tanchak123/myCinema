package mycinema.security;

import mycinema.exceptions.AuthenticationException;
import mycinema.model.User;

public interface SecurityService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
