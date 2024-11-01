package org.example.lab8_20210795.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "registro")
@Getter
@Setter
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegistro", nullable = false)
    private Integer idRegistro;

    @ManyToOne
    @JoinColumn(name = "Eventos_idEventos", nullable = false)
    private Eventos evento;

    @ManyToOne
    @JoinColumn(name = "Persona_idPersona", nullable = false)
    private Persona persona;
}
