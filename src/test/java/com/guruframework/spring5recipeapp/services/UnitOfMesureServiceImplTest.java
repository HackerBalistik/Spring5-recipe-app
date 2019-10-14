package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import com.guruframework.spring5recipeapp.converters.UnitOfMesureToUnitOfMesureCommand;
import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import com.guruframework.spring5recipeapp.repository.UnitOfMesureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMesureServiceImplTest {

    @Mock
    UnitOfMesureRepository unitOfMesureRepository;

    UnitOfMesureService unitOfMesureService;
    //surtout ne pas oubliez l'initialisation
    UnitOfMesureToUnitOfMesureCommand unitOfMesureToUnitOfMesureCommand = new UnitOfMesureToUnitOfMesureCommand();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMesureService = new UnitOfMesureServiceImpl(unitOfMesureRepository, unitOfMesureToUnitOfMesureCommand);
    }

    @Test
    public void listAllUom() {
        //given
        Set<UnitOfMesure> uomList = new HashSet<>();
        UnitOfMesure unit1 = new UnitOfMesure();
        unit1.setId(1L);
        UnitOfMesure unit2 = new UnitOfMesure();
        unit2.setId(2L);

        uomList.add(unit1);
        uomList.add(unit2);

        //when
        when(unitOfMesureRepository.findAll()).thenReturn(uomList);
        Set<UnitOfMesureCommand> uomCommandList = unitOfMesureService.ListAllUom();

        //then
        assertEquals(2, uomCommandList.size());
        verify(unitOfMesureRepository, times(1)).findAll();
    }
}