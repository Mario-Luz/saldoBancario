package com.saldobancario.saldobancario.repository;

import com.saldobancario.saldobancario.model.SaldoBancarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaldoBancarioRepository extends CrudRepository<SaldoBancarioModel, UUID> {
}