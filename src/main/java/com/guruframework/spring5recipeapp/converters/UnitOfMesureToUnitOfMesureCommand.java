package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMesureToUnitOfMesureCommand implements Converter<UnitOfMesure, UnitOfMesureCommand> {

    @Nullable
    @Synchronized
    @Override
    public UnitOfMesureCommand convert(UnitOfMesure source) {
        if (source == null){
            return null;
        }

        final  UnitOfMesureCommand uomCommand = new UnitOfMesureCommand();
        uomCommand.setId(source.getId());
        uomCommand.setDescription(source.getDescription());

        return uomCommand;
    }
}
