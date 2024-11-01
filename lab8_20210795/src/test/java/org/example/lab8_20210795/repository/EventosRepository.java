package org.example.lab8_20210795.repository;

import org.example.lab8_20210795.entity.Eventos;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventosRepository {
    List<Eventos> findAll();
}
