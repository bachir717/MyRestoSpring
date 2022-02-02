package eu.ensup.MyResto.domaine;

import eu.ensup.MyResto.model.States;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Orders {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float price;
    private Date created;
    private Date delivered;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products;
    private States sate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Orders() {
    }

    public Orders(Float price, Date created, Date delivered, List<Product> products, States sate, User user) {
        this.price = price;
        this.created = created;
        this.delivered = delivered;
        this.products = products;
        this.sate = sate;
        this.user = user;
    }
}
