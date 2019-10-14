package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import com.guruframework.spring5recipeapp.converters.UnitOfMesureToUnitOfMesureCommand;
import com.guruframework.spring5recipeapp.repository.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMesureServiceImpl implements UnitOfMesureService {

    private final UnitOfMesureRepository unitOfMesureRepository;
    private final UnitOfMesureToUnitOfMesureCommand unitOfMesureToUnitOfMesureCommand;

    public UnitOfMesureServiceImpl(UnitOfMesureRepository unitOfMesureRepository,
                                   UnitOfMesureToUnitOfMesureCommand unitOfMesureToUnitOfMesureCommand) {
        this.unitOfMesureRepository = unitOfMesureRepository;
        this.unitOfMesureToUnitOfMesureCommand = unitOfMesureToUnitOfMesureCommand;
    }

    @Override
    public Set<UnitOfMesureCommand> ListAllUom() {
        return StreamSupport.stream(unitOfMesureRepository.findAll()
                            .spliterator(), false)
                            .map(unitOfMesureToUnitOfMesureCommand :: convert)
                            .collect(Collectors.toSet());
    }
}
