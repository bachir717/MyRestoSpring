package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.MyRestoApplication;
import eu.ensup.MyResto.domaine.Opinions;
import eu.ensup.MyResto.model.Types;
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
class OpinionsRepositoryTest {
    @Autowired
    private OpinionsRepository opinionsRepository;

    @Test
    public void TestSaveOpinions() {
        Opinions opinions = new Opinions(1L, "commentaire");
        Opinions retrunSave =  opinionsRepository.save(opinions);
        assertThat(opinions.getComment(), equalTo(retrunSave.getComment()));
    }

    @Test
    public void TestGetAllOpinions() {
        for (int i=1 ; i < 10 ; i++)
            opinionsRepository.save(new Opinions(Long.valueOf(i),"Miam *"+i));

        List<Opinions> allOpinions = opinionsRepository.findAll();
        assertThat(9, equalTo(allOpinions.size()));
    }

    @Test
    public void TestGetOneUserByID() {
        for (int i=1; i < 10 ; i++)
            opinionsRepository.save(new Opinions(Long.valueOf(i),"Miam *"+i));

        Opinions opinions = opinionsRepository.getById(3L);
        assertThat(3L, equalTo(opinions.getId()));
    }
}