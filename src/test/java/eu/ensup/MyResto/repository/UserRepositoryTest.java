package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MyRestoApplication.class})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void TestSaveUser() {
        User user = new User(1L,"name","LastName","email","address", Roles.USER,"mdp","",true,null);
        User retrunSave =  userRepository.save(user);
        assertThat(user.getUsername(), equalTo(retrunSave.getUsername()));
    }

    @Test
    public void TestGetAllUsers() {
        for (int i=1 ; i < 10 ; i++)
            userRepository.save(new User(Long.valueOf(i),"name "+i, "LastName","email","address", Roles.USER,"mdp","",true,null));

        List<User> allUsers = userRepository.findAll();
        assertThat(9, equalTo(allUsers.size()));
    }

    @Test
    public void TestGetOneUserByID() {
        for (int i=1; i < 10 ; i++)
            userRepository.save(new User(Long.valueOf(i),"name "+i, "LastName","email","address", Roles.USER,"mdp","",true,null));

        User user = userRepository.getById(3L);
        assertThat(3L, equalTo(user.getId()));
    }

    @Test
    public void TestDeleteOneUserByID() {
        for (int i=1; i < 10 ; i++)
            userRepository.save(new User(Long.valueOf(i),"name "+i, "LastName","email","address", Roles.USER,"mdp","",true,null));

        userRepository.deleteById(3L);
        List<User> allUsers = userRepository.findAll();
        assertThat(8, equalTo(allUsers.size()));
    }

    @Test
    public void TestDeleteAllUser() {
        for (int i=1; i < 10 ; i++)
            userRepository.save(new User(Long.valueOf(i),"name "+i, "LastName","email","address", Roles.USER,"mdp","",true,null));

        userRepository.deleteAll();
        List<User> allUsers = userRepository.findAll();
        assertThat(0, equalTo(allUsers.size()));
    }
}