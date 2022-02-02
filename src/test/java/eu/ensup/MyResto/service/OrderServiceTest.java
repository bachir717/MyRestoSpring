package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Opinions;
import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.model.Types;
import eu.ensup.MyResto.repository.OpinionsRepository;
import eu.ensup.MyResto.repository.OrdersRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    @DisplayName("Test get all orders")
    public void testGetAll(){
        List<Orders> listOrders = new ArrayList<>();
        for (int i=0 ; i < 10 ; i++)
            listOrders.add(new Orders(Long.valueOf(i), 4.12f, Date.valueOf("2022-01-01"), null, States.CREATED, null));

        // GIVEN
        when(ordersRepository.findAll()).thenReturn(listOrders);
        // WHEN
        final Iterable<Orders> result = orderService.getAll();
        int size = 0;
        if(result != null && result instanceof Collection) {
            size = ((Collection<?>) result).size();
        }
        MatcherAssert.assertThat("Test fail : ", listOrders.size(), Matchers.equalTo(size));
        // THEN
        verify(ordersRepository).findAll();
    }

    @Test
    @DisplayName("Test save an order")
    public void testSave() {
        Orders order = new Orders(0L, 4.12f, Date.valueOf("2022-01-01"), null, States.CREATED, null);

        // GIVEN
        when(ordersRepository.save(order)).thenReturn(order);
        // WHEN
        MatcherAssert.assertThat("Test fail : ", orderService.save(order));
        // THEN
        verify(ordersRepository).save(order);
    }

    @Test
    @DisplayName("Test get an order by id")
    public void testGetOne() {
        Orders order = new Orders(0L, 4.12f, Date.valueOf("2022-01-01"), null, States.CREATED, null);

        // GIVEN
        when(ordersRepository.findById(order.getId())).thenReturn(Optional.of(order));
        // WHEN
        final Orders result = orderService.getOne(order.getId());
        MatcherAssert.assertThat("Test fail : ", order.toString(), Matchers.equalTo(result.toString()));
        // THEN
        verify(ordersRepository).findById(order.getId());
    }
}