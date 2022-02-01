package eu.ensup.MyResto.domaine;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Orders {

    @Id
    private Long id;
    private Float price;
    private Date created;
    private Date delivered;
    @ManyToMany
    private Set<Product> products;
}
