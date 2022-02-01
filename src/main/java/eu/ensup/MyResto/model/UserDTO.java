package eu.ensup.MyResto.model;


import eu.ensup.MyResto.domaine.Orders;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String lastName;
    private String email;
    private String address;
    private String role; // changer mettre l'enum
    private String password;
    private String picture;
    private List<Orders> orders = new ArrayList<>();
}
