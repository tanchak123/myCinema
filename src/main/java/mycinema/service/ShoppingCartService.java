package mycinema.service;

import mycinema.model.MovieSession;
import mycinema.model.ShoppingCart;
import mycinema.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);
}
