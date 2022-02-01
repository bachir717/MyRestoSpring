package eu.ensup.MyResto.TestDao;


import eu.ensup.MyResto.MyRestoApplication;
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
        User alex = new User("alex2");
        User repo =  userRepository.save(alex);
        assertThat(repo.getUsername()).isEqualTo(alex.getUsername());
    }

    @Test
    public void TestSaveUserAndFinbyUsername() {
        User alex = new User("alex");
        User repo =  userRepository.save(alex);
        Optional<User> found = userRepository.findByUsername(alex.getUsername());
        assertThat(found.get().getUsername()).isEqualTo(alex.getUsername());
    }



}
