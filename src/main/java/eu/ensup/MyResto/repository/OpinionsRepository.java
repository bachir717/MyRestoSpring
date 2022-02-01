package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.domaine.Opinions;
import eu.ensup.MyResto.domaine.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionsRepository extends JpaRepository<Opinions, Long> {
}
