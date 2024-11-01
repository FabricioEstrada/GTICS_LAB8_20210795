package org.example.lab8_20210795.repository;

import org.example.lab8_20210795.entity.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, Integer> {
    List<Eventos> findAll();
}
