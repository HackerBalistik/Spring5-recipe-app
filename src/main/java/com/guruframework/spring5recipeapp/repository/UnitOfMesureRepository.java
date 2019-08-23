package com.guruframework.spring5recipeapp.repository;

import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMesureRepository extends CrudRepository<UnitOfMesure, Long> {

    Optional<UnitOfMesure> findByDescription(String UnitOfMesure);
}
