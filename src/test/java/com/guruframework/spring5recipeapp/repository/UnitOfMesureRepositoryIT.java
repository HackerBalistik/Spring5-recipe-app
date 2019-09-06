package com.guruframework.spring5recipeapp.repository;

import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMesureRepositoryIT {

    @Autowired
    UnitOfMesureRepository unitOfMesureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @DirtiesContext
    public void findByDescription() {
        Optional<UnitOfMesure> unitOfMesure = unitOfMesureRepository.findByDescription("Tablespoon");

        assertEquals("Tablespoon", unitOfMesure.get().getDescription());
    }

    @Test
    public void findByDescriptionPinch() {
        Optional<UnitOfMesure> unitOfMesure = unitOfMesureRepository.findByDescription("Pinch");

        assertEquals("Pinch", unitOfMesure.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMesure> unitOfMesure = unitOfMesureRepository.findByDescription("Cup");

        assertEquals("Cup", unitOfMesure.get().getDescription());
    }
}