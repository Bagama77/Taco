package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
//    Order save(Order order);
}
