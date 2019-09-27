package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.NotesCommand;
import com.guruframework.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    public static final Long LONG_VALUE = 1L;
    public static final String RECIPE_NOTE ="note";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void TestNullParam() {
        assertNull(converter.convert(null));
    }

    @Test
    public void TestEmptyParam() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(LONG_VALUE);
        command.setRecipeNotes(RECIPE_NOTE);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(LONG_VALUE, notes.getId());
        assertEquals(RECIPE_NOTE, notes.getRecipeNotes());
    }
}