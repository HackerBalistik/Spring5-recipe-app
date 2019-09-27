package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMesureCommandToUnitOfMesureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1L;

    UnitOfMesureCommandToUnitOfMesure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMesureCommandToUnitOfMesure();
    }

    @Test
    public void TestNullParam() {
        UnitOfMesure uom = converter.convert(null);
        assertNull(uom);
    }

    @Test
    public void TestEmptyParam() {
        UnitOfMesure uom = converter.convert(new UnitOfMesureCommand());
        assertNotNull(uom);
    }

    @Test
    public void convert() {
        //given
        UnitOfMesureCommand command = new UnitOfMesureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMesure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}