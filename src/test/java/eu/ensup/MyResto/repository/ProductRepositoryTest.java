package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.model.Types;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRestoApplication.class)
class ProductRepositoryTest {
    private ProductRepository productRepository;

    @Test
    public void TestSaveProduct() {
        Product product = new Product(0L,"Crepe", 4.12f, Types.PLAT,null);
        Product retrunSave =  productRepository.save(product);
        assertThat(product.getName(), equalTo(retrunSave.getName()));
    }

    @Test
    public void TestGetAllProducts() {
        for (int i=0 ; i < 10 ; i++)
            productRepository.save(new Product(Long.valueOf(i),"Crepe "+i, 4.12f,Types.PLAT,null));

        List<Product> allProducts = productRepository.findAll();
        assertThat(10, equalTo(allProducts.size()));
    }

    @Test
    public void TestGetOneUserByID() {
        productRepository.save(new Product(3L,"Crepe 3", 4.12f,Types.PLAT,null));

        for (int i=0; i < 10 ; i++)
            productRepository.save(new Product(Long.valueOf(i),"Crepe "+i, 4.12f,Types.PLAT,null));

        Product product = productRepository.getById(1L);

        assertThat("Crepe 3", equalTo(product.getName()));
    }
}