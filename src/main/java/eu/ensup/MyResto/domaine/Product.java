package eu.ensup.MyResto.domaine;

import eu.ensup.MyResto.model.Types;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Float price;
    private Types type;
    private String picture;

    public Product() {

    }

    public Product(String name, Float price, Types type, String picture) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.picture = picture;
    }
}
