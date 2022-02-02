package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.model.States;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MyRestoApplication.class})
class OrdersRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void TestSaveProduct() {
        Orders order = new Orders(1L, 4.12f, Date.valueOf("2022-01-01"), null, null, States.CREATED, null);
        Orders retrunSave =  ordersRepository.save(order);
        assertThat(order.getId(), equalTo(retrunSave.getId()));
    }

    @Test
    public void TestGetAllProducts() {
        for (int i=1 ; i < 10 ; i++)
            ordersRepository.save(new Orders(Long.valueOf(i), 4.12f, Date.valueOf("2022-01-01"), null, null, States.CREATED, null));

        List<Orders> allOrders = ordersRepository.findAll();
        assertThat(9, equalTo(allOrders.size()));
    }

    @Test
    public void TestGetOneUserByID()
    {
        for (int i=0; i < 10 ; i++)
            ordersRepository.save(new Orders(Long.valueOf(i), 4.12f, Date.valueOf("2022-01-01"), null, null, States.CREATED, null));

        Orders orders = ordersRepository.getById(1L);

        assertThat(1L, equalTo(orders.getId()));
    }
}