package mycinema.dao;

import java.util.List;
import mycinema.model.Order;

public interface OrderDao {

    Order add(Order order);

    List<Order> getAll();
}
