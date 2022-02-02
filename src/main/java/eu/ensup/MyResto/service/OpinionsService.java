package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Opinions;
import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.repository.OpinionsRepository;
import eu.ensup.MyResto.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OpinionsService {

    @Autowired
    private OpinionsRepository opinionsRepository;

    public Iterable<Opinions> getAll(){
        return opinionsRepository.findAll();
    }

    public boolean save(Opinions opinions) {
        return opinionsRepository.save(opinions) != null;
    }

    public Optional<Opinions> getOne(Long productID) {
       return opinionsRepository.findById(productID);
    }
}
