package mycinema.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import mycinema.dao.OrderDao;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.Order;
import mycinema.model.Ticket;
import mycinema.model.User;
import mycinema.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTicket(tickets);
        order.setUser(user);
        order.setCreatedTime(LocalDateTime.now().withNano(0));
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        List<Order> orders = orderDao.getAll().stream()
                .filter(order -> order.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
        return orders;
    }
}
