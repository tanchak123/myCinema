package mycinema.service;

import java.util.List;
import mycinema.model.Order;
import mycinema.model.Ticket;
import mycinema.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
