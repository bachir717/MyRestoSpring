package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.model.Types;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MyRestoApplication.class})
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void TestSaveProduct() {
        Product product = new Product(1L,"Crepe", 4.12f, Types.PLAT,null);
        Product retrunSave =  productRepository.save(product);
        assertThat(product.getName(), equalTo(retrunSave.getName()));
    }

    @Test
    public void TestGetAllProducts() {
        for (int i=1 ; i < 10 ; i++)
            productRepository.save(new Product(Long.valueOf(i),"Crepe "+i, 4.12f,Types.PLAT,null));

        List<Product> allProducts = productRepository.findAll();
        assertThat(9, equalTo(allProducts.size()));
    }

    @Test
    public void TestGetOneUserByID() {
        for (int i=1; i < 10 ; i++)
            productRepository.save(new Product(Long.valueOf(i),"Crepe "+i, 4.12f,Types.PLAT,null));

        Product product = productRepository.getById(3L);
        assertThat(3L, equalTo(product.getId()));
    }
}