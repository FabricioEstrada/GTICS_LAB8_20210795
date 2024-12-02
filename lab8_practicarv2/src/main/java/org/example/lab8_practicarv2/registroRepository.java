package org.example.lab8_practicarv2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface registroRepository extends JpaRepository<registro,Integer> {
}
