package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMesureToUnitOfMesureCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1L;

    UnitOfMesureToUnitOfMesureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMesureToUnitOfMesureCommand();
    }

    @Test
    public void TestNullParam() throws Exception{
        UnitOfMesureCommand command = converter.convert(null);
        assertNull(command);
    }

    @Test
    public void TestEmptyParam() throws Exception{
        UnitOfMesureCommand command = converter.convert(new UnitOfMesure());
        assertNotNull(command);
    }

    @Test
    public void convert() throws Exception{
        //given
        UnitOfMesure uom = new UnitOfMesure();
        uom.setId(LONG_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMesureCommand command = converter.convert(uom);

        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}