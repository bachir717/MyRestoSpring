//package eu.ensup.MyResto.repository;
//
//
//import eu.ensup.MyResto.MyRestoApplication;
//import eu.ensup.MyResto.model.Roles;
//import eu.ensup.MyResto.domaine.User;
//import eu.ensup.MyResto.repository.UserRepository;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MyRestoApplication.class)
//public class UserTest {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void TestSaveUser() {
//        User user = new User(0L,"Flavien","Annaix","Flavien.annaix@gmail.com","24 bis", Roles.USER,"azerty","");
//        User retrunSave =  userRepository.save(user);
//        assertThat(retrunSave.getUsername()).isEqualTo(user.getUsername());
//    }
//
//    @Test
//    public void TestSaveUserAndFinbyUsername() {
//        User user = new User(0L,"USerName","Annaix","Flavien.annaix@gmail.com","24 bis", Roles.USER,"azerty","");
//        User repo =  userRepository.save(user);
//        Optional<User> found = userRepository.findByUsername(user.getUsername());
//        assertThat(found.get().getUsername()).isEqualTo(user.getUsername());
//    }
//
//    @Test
//    public void TestGetAllUsers() {
//        for (int i =0 ; i < 10 ; i++)
//            userRepository.save(new User(0L,"Flavien"+i,"Annaix"+i,"Flavien.annaix@gmail.com"+i,"24 bis", Roles.USER,"azerty",""));
//
//        List<User> allUsers = userRepository.findAll();
//        assertThat(allUsers.size()).isEqualTo(10);
//    }
//    @Test
//    @Order(1)
//    public void TestGetOneUserByID() {
//
//
//        for (int i =2 ; i < 10 ; i++)
//            userRepository.save(new User(0L,"Flavien"+i,"Annaix"+i,"Flavien.annaix@gmail.com"+i,"24 bis", Roles.USER,"azerty",""));
//
//        User user = userRepository.getById(1L);
//
//        assertThat(user.getUsername()).isEqualTo("Flavien0");
//    }
//
//    @Test
//    @AfterAll
//    public void TestDeleteUser() {
//
//        List<User> allUsersBefore = userRepository.findAll();
//        userRepository.deleteById(1L);
//        List<User> allUsersAfter = userRepository.findAll();
//        assertThat(allUsersAfter.size()).isEqualTo(allUsersBefore.size()-1);
//    }
//    public void TestDeleteAllUser() {
//
//        userRepository.deleteAll();
//        List<User> allUsersAfter = userRepository.findAll();
//        assertThat(allUsersAfter.size()).isEqualTo(0);
//    }
//}
