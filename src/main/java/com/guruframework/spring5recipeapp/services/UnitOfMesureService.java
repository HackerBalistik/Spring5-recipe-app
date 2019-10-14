package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UnitOfMesureService {

    Set<UnitOfMesureCommand> ListAllUom();
}
