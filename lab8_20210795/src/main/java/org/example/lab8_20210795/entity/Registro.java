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

    @Column(name = "NombreApellidoPersona", nullable = false, length = 100)
    private String nombreApellido;

    @Column(name = "CorreoPersona", nullable = false, length = 100)
    private String correo;


    @Column(name = "NumCuposReserva", nullable = false)
    private Integer numeroCuposReserva;

    @ManyToOne
    @JoinColumn(name = "Eventos_idEventos", nullable = false)
    private Eventos evento;

}
