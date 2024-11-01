package org.example.lab8_20210795.controller;

import org.example.lab8_20210795.entity.Eventos;
import org.example.lab8_20210795.repository.EventosRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    final EventosRepository eventosRepository;
    public EventoController(EventosRepository eventosRepository) {
        this.eventosRepository = eventosRepository;
    }

    //LISTAR
    @GetMapping(value = {"/list", ""})
    public List<Eventos> listaEventos() {
        return eventosRepository.findAll();
    }
}
