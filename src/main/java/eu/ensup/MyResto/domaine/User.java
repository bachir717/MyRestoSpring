package eu.ensup.MyResto.domaine;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String role; // changer mettre l'enum
    private String password;
    private String picture;
    @OneToMany
    private List<Orders> orders = new ArrayList<>();
}
