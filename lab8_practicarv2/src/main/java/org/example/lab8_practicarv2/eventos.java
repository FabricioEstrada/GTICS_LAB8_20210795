package org.example.lab8_practicarv2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "eventos")
public class eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventos", nullable = false)
    private Integer idEventos;

    @Column(name = "Nombre", nullable = false)
    private String Nombre;

    @Column(name = "Fecha", nullable = false)
    private Date Fecha;

    @Column(name = "Categoria", nullable = false)
    private String Categoria;

    @Column(name = "CapacidadMax", nullable = false)
    private Integer CapacidadMax;

    @Column(name = "NumReservasActual", nullable = false)
    private Integer NumReservasActual;
}
