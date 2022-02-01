package eu.ensup.MyResto.domaine;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class Product {
    @Id
    private Long id;
    private Float price;
    private String type;
    private String picture;

}
