package org.example.lab8_practicarv2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registro")
public class registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegistro", nullable = false)
    private Integer idRegistro;

    @Column(name = "NombreApellidoPersona", nullable = false)
    private String NombreApellidoPersona;

    @Column(name = "CorreoPersona", nullable = false)
    private String CorreoPersona;

    @Column(name = "NumCuposReserva", nullable = false)
    private Integer NumCuposReserva;

    @ManyToOne
    @JoinColumn(name = "Eventos_idEventos", nullable = false)
    private eventos Eventos;
}
