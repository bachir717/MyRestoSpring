package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.model.Types;
import eu.ensup.MyResto.repository.ProductRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Test get all product")
    public void testGetAll(){
        List<Product> listProduct = new ArrayList<>();
        for (int i=0 ; i < 10 ; i++)
            listProduct.add(new Product(Long.valueOf(i), "Crepe", 4.12f, Types.PLAT,null));

        // GIVEN
        when(productRepository.findAll()).thenReturn(listProduct);
        // WHEN
        final Iterable<Product> result = productService.getAll();
        int size = 0;
        if(result != null && result instanceof Collection) {
            size = ((Collection<?>) result).size();
        }
        MatcherAssert.assertThat("Test fail : ", listProduct.size(), Matchers.equalTo(size));
        // THEN
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("Test save an product")
    public void testSave() {
        Product product = new Product(0L,"Crepe", 4.12f, Types.PLAT,null);

        // GIVEN
        when(productRepository.save(product)).thenReturn(product);
        // WHEN
        MatcherAssert.assertThat("Test fail : ", productService.save(product));
        // THEN
        verify(productRepository).save(product);
    }

    @Test
    @DisplayName("Test get an product by id")
    public void testGetOne() {
        Product product = new Product(0L,"Crepe", 4.12f, Types.PLAT,null);

        // GIVEN
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        // WHEN
        final Product result = productService.getOne(product.getId());
        MatcherAssert.assertThat("Test fail : ", product.toString(), Matchers.equalTo(result.toString()));
        // THEN
        verify(productRepository).findById(product.getId());
    }
}