package com.zipcodewilmington.bakery;

import com.zipcodewilmington.bakery.Controllers.BakerController;
import com.zipcodewilmington.bakery.Models.Baker;
import com.zipcodewilmington.bakery.Repositories.BakerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

/**
 * @author leon on 8/30/18.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BakeryApplication.class)
public class BakerControllerTest {
    @MockBean
    private BakerRepository repo;

    private BakerController controller;

    @Before
    public void setup(){
        this.controller = new BakerController(repo);
    }


    @Test
    public void testCreate() {
        // Given
        HttpStatus expected = HttpStatus.CREATED;
        Baker expectedBaker = new Baker(null, null, null);
        given(repo.save(expectedBaker)).willReturn(expectedBaker);

        // When
        ResponseEntity<Baker> response = controller.create(expectedBaker);
        HttpStatus actual = response.getStatusCode();
        Baker actualBaker = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedBaker, actualBaker);
    }
}
