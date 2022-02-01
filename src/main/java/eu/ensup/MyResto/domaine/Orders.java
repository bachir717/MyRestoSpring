package eu.ensup.MyResto.domaine;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float price;
    private Date created;
    private Date delivered;
    @ManyToMany
    private Set<Product> products;
}
