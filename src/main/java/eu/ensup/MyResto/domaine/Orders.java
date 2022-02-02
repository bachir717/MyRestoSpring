package eu.ensup.MyResto.domaine;

import eu.ensup.MyResto.model.States;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float price;
    private Date created;
    private Date delivered;
    private States state;
    @ManyToMany
    private Set<Product> products;

    public Orders() {

    }
}
