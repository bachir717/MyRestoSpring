package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.repository.ProductRepository;
import eu.ensup.MyResto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAll(){
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> getOneProduct(Long productID) {
       return productRepository.findById(productID);
    }
}
