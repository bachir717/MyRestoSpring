package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.model.States;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRestoApplication.class)
class OrdersRepositoryTest {
    private OrdersRepository ordersRepository;

    @Test
    public void TestSaveProduct() {
        Product product = new Product(0L,"Crepe", 4.12f,"Plat", null);
        Orders order = new Orders(0L, 4.12f, Date.valueOf("2022-01-01"), null, States.CREATED, new HashSet<>(Arrays.asList(product)));
        Orders retrunSave =  ordersRepository.save(order);
        assertThat(order.getId(), equalTo(retrunSave.getId()));
    }

    @Test
    public void TestGetAllProducts() {
        for (int i=0 ; i < 10 ; i++)
            ordersRepository.save(new Orders(0L, 4.12f, Date.valueOf("2022-01-01"), null, States.CREATED, null));

        List<Orders> allOrders = ordersRepository.findAll();
        assertThat(10, equalTo(allOrders.size()));
    }

    @Test
    public void TestGetOneUserByID()
    {
        for (int i=0; i < 10 ; i++)
            ordersRepository.save(new Orders(Long.valueOf(i), 4.12f, Date.valueOf("2022-01-01"), null, States.CREATED, null));

        Orders orders = ordersRepository.getById(1L);

        assertThat(1L, equalTo(orders.getId()));
    }
}