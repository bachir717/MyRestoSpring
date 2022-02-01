package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.repository.OrdersRepository;
import eu.ensup.MyResto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    public Iterable<Orders> getAll(){
        return ordersRepository.findAll();
    }

    public void save(Orders orders) {
        ordersRepository.save(orders);
    }

    public Optional<Orders> getOne(Long productID) {
       return ordersRepository.findById(productID);
    }
}
