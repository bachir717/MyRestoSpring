package eu.ensup.MyResto.model;


import eu.ensup.MyResto.domaine.Orders;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String lastName;
    private String email;
    private String address;
    private Roles role; // changer mettre l'enum
    private String picture;
    private List<Orders> orders = new ArrayList<>();
}
