package org.example.lab8_20210795.repository;

import org.example.lab8_20210795.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
}
