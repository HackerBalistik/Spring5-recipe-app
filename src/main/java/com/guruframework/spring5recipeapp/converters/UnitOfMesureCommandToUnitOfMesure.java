package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMesureCommandToUnitOfMesure implements Converter<UnitOfMesureCommand, UnitOfMesure> {

    @Nullable
    @Synchronized
    @Override
    public UnitOfMesure convert(UnitOfMesureCommand source) {
        if (source == null) {
            return null;
        }

        final  UnitOfMesure uom = new UnitOfMesure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());

        return uom;
    }
}
