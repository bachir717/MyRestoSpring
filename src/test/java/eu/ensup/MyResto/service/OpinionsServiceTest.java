package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Opinions;
import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.repository.OpinionsRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpinionsServiceTest {
    @Mock
    private OpinionsRepository opinionsRepository;

    @InjectMocks
    private OpinionsService opinionsService;

    @Test
    @DisplayName("Test get all opinion")
    public void testGetAll()
    {
        List<Opinions> listOpinions = new ArrayList<>();
        for (int i=0 ; i < 10 ; i++)
            listOpinions.add(new Opinions(Long.valueOf(i), "commentaire"));

        // GIVEN
        when(opinionsRepository.findAll()).thenReturn(listOpinions);
        // WHEN
        final Iterable<Opinions> result = opinionsService.getAll();
        int size = 0;
        if(result != null && result instanceof Collection) {
            size = ((Collection<?>) result).size();
        }

        MatcherAssert.assertThat("Test fail : ", listOpinions.size(), Matchers.equalTo(size));
        // THEN
        verify(opinionsRepository).findAll();
    }

    @Test
    @DisplayName("Test save an opinion")
    public void testSave() {
        Opinions opinions = new Opinions(1L, "commentaire");

        // GIVEN
        when(opinionsRepository.save(opinions)).thenReturn(opinions);
        // WHEN
        MatcherAssert.assertThat("Test fail : ", opinionsService.save(opinions));
        // THEN
        verify(opinionsRepository).save(opinions);
    }

    @Test
    @DisplayName("Test get an opinion by id")
    public void getOne() {
        Opinions opinions = new Opinions(1L, "commentaire");

        // GIVEN
        when(opinionsRepository.findById(opinions.getId())).thenReturn(Optional.of(opinions));
        // WHEN
        final Opinions result = opinionsService.getOne(opinions.getId());
        MatcherAssert.assertThat("Test fail : ", opinions.toString(), Matchers.equalTo(result.toString()));
        // THEN
        verify(opinionsRepository).findById(opinions.getId());
    }
}