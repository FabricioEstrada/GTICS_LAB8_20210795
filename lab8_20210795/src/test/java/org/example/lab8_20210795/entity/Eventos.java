package org.example.lab8_20210795.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "eventos")
@Getter
@Setter
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventos", nullable = false)
    private Integer idEventos;
    @Column(name = "Nombre", nullable = false, length = 45)
    private String nombreEvento;
    @Column(name = "Categoria", nullable = false, length = 45)
    private String categoria;
    @Column(name = "CapacidadMax", nullable = false)
    private Integer capacidadMax;
    @Column(name = "NumReservasActual", nullable = false)
    private Integer numReservasActual;
    @Column(name = "Fecha", nullable = false)
    private Date fecha;
}
