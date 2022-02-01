package eu.ensup.MyResto.TestRepo;


import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRestoApplication.class)
public class UserTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void TestSaveUser() {
        User user = new User(0L,"Flavien","Annaix","Flavien.annaix@gmail.com","24 bis", Roles.USER,"azerty","",null);
        User retrunSave =  userRepository.save(user);
        assertThat(retrunSave.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void TestSaveUserAndFinbyUsername() {
        User user = new User(0L,"USerName","Annaix","Flavien.annaix@gmail.com","24 bis", Roles.USER,"azerty","",null);
        User repo =  userRepository.save(user);
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        assertThat(found.get().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void TestGetAllUsers() {
        for (int i =0 ; i < 10 ; i++)
            userRepository.save(new User(0L,"Flavien"+i,"Annaix"+i,"Flavien.annaix@gmail.com"+i,"24 bis", Roles.USER,"azerty","",null));

        List<User> allUsers = userRepository.findAll();
        assertThat(allUsers.size()).isEqualTo(10);
    }

    @Test
    @Ignore
    public void TestGetOneUserByID() {

        userRepository.save(new User(0L,"user1","Annaix","Flavien.annaix@gmail.com","24 bis", Roles.USER,"azerty","",null));

        for (int i =2 ; i < 10 ; i++)
            userRepository.save(new User(0L,"Flavien"+i,"Annaix"+i,"Flavien.annaix@gmail.com"+i,"24 bis", Roles.USER,"azerty","",null));

        User user = userRepository.getById(1L);

        assertThat(user.getUsername()).isEqualTo("user1");
    }

}
