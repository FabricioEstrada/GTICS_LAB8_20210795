package org.example.lab8_20210795.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona", nullable = false)
    private Integer idPersona;
    @Column(name = "Nombre", nullable = false, length = 45)
    private String nombrePersona;
    @Column(name = "Correo", nullable = false, length = 100)
    private String correo;
}
