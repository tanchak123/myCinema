package mycinema.service.serviceimpl;

import java.util.ArrayList;
import mycinema.dao.ShoppingCartDao;
import mycinema.dao.TicketDao;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.MovieSession;
import mycinema.model.ShoppingCart;
import mycinema.model.Ticket;
import mycinema.model.User;
import mycinema.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    ShoppingCartDao shoppingCartDao;

    @Inject
    TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getTickets().add(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCart.setUser(user);
        shoppingCart.setId(user.getId());
        shoppingCartDao.add(shoppingCart);
    }
}
