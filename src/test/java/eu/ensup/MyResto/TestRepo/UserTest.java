package eu.ensup.MyResto.TestRepo;


import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
        User user = new User(0L,"Flavien2","Annaix","Flavien.annaix@gmail.com","24 bis", Roles.USER,"azerty","",null);
        User repo =  userRepository.save(user);
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        assertThat(found.get().getUsername()).isEqualTo(user.getUsername());
    }



}
