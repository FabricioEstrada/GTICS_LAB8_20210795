package org.example.lab8_20210795.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.lab8_20210795.entity.Eventos;
import org.example.lab8_20210795.entity.Registro;
import org.example.lab8_20210795.repository.EventosRepository;
import org.example.lab8_20210795.repository.RegistroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    final EventosRepository eventosRepository;
    final RegistroRepository registroRepository;
    public EventoController(EventosRepository eventosRepository, RegistroRepository registroRepository) {
        this.eventosRepository = eventosRepository;
        this.registroRepository = registroRepository;
    }

    @GetMapping(value = {"/list", ""})
    public ResponseEntity<HashMap<String, Object>> listaEventosPorFecha(@RequestParam(required = false) String fecha) {
        HashMap<String, Object> responseJson = new HashMap<>();
        Date fechaDate = null;

        try {
            if (fecha != null) {
                // Validando formato
                if (!fecha.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    responseJson.put("result", "failure");
                    responseJson.put("mensaje", "Formato de fecha inválido. Utilice 'dd-MM-yyyy'.");
                    return ResponseEntity.badRequest().body(responseJson);
                }
                try {
                    fechaDate = new SimpleDateFormat("dd-MM-yyyy").parse(fecha); // Formato de fecha en español
                } catch (ParseException e) {
                    responseJson.put("result", "failure");
                    responseJson.put("msg", "Formato de fecha inválido. Utilice 'dd-MM-yyyy'.");
                    return ResponseEntity.badRequest().body(responseJson);
                }
            }

            List<Eventos> eventos;
            if (fechaDate != null) {
                eventos = eventosRepository.findByFecha(fechaDate); // Filtrar por fecha si se proporciona
            } else {
                eventos = eventosRepository.findAllByOrderByFechaAsc(); // Lista de eventos ordenados por fecha
            }

            responseJson.put("result", "success");
            responseJson.put("eventos", eventos);
            return ResponseEntity.ok(responseJson);

        } catch (Exception e) {
            responseJson.put("result", "failure");
            responseJson.put("msg", "Ocurrió un error al procesar la solicitud.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<HashMap<String, Object>> crearEvento(@RequestBody Eventos nuevoEvento,
    @RequestParam(value = "fetchId", required = false) boolean fetchId) {
        HashMap<String, Object> responseJson = new HashMap<>();
        Date fechaActual = new Date();

        try {
            // Validar todos los campos se hayan colocado
            if (nuevoEvento.getNombreEvento() == null || nuevoEvento.getNombreEvento().isEmpty() ||
                    nuevoEvento.getCategoria() == null || nuevoEvento.getCategoria().isEmpty() ||
                    nuevoEvento.getCapacidadMax() == null ||
                    nuevoEvento.getFecha() == null) {

                responseJson.put("resultado", "failure");
                responseJson.put("mensaje", "Todos los campos (nombreEvento, categoria, capacidadMax, fecha) son obligatorios.");
                return ResponseEntity.badRequest().body(responseJson);
            }

            // Validar fecha a futuro
            if (nuevoEvento.getFecha().before(fechaActual)) {
                responseJson.put("resultado", "failure");
                responseJson.put("mensaje", "La fecha del evento debe ser en el futuro o asegurese que el formato sea 'dd-MM-yyyy'.");
                return ResponseEntity.badRequest().body(responseJson);
            }

            // Iniciar con reservas cero
            nuevoEvento.setNumReservasActual(0);

            // Guardar el evento en la base de datos
            Eventos eventoGuardado = eventosRepository.save(nuevoEvento);

            responseJson.put("resultado", "succesful");
            responseJson.put("estado", "¡creado!");

            if (fetchId) {
                responseJson.put("id",eventoGuardado.getIdEventos());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);

        } catch (Exception e) {
            responseJson.put("resultado", "failure");
            responseJson.put("mensaje", "Ocurrió un error al crear el evento.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
        }
    }




    @PostMapping("/registrar")
    public ResponseEntity<HashMap<String, Object>> reservarLugar(@RequestBody Registro nuevoRegistro) {
        HashMap<String, Object> responseJson = new HashMap<>();

        try {
            // Validando que se hayan pasado correctamente los datos al registro
            if (nuevoRegistro.getEvento() == null ||
                    nuevoRegistro.getNumeroCuposReserva() <= 0 ||
                    nuevoRegistro.getNombreApellido() == null || nuevoRegistro.getNombreApellido().isEmpty() ||
                    nuevoRegistro.getCorreo() == null || nuevoRegistro.getCorreo().isEmpty()) {

                responseJson.put("resultado", "failure");
                responseJson.put("mensaje", "Datos de reserva inválidos. Todos los campos (evento, numeroCuposReserva, nombreApellidoPersona y correoPersona) son obligatorios.");
                return ResponseEntity.badRequest().body(responseJson);
            }

            Eventos evento = eventosRepository.findById(nuevoRegistro.getEvento().getIdEventos()).orElse(null);
            if (evento == null) {
                responseJson.put("resultado", "failure");
                responseJson.put("mensaje", "El evento no existe.");
                return ResponseEntity.badRequest().body(responseJson);
            }

            // Validando cupos disponibles
            int reservasActuales = evento.getNumReservasActual();
            int capacidadMaxima = evento.getCapacidadMax();
            int nuevosCupos = nuevoRegistro.getNumeroCuposReserva();

            if (reservasActuales + nuevosCupos > capacidadMaxima) {
                responseJson.put("resultado", "failure");
                responseJson.put("mensaje", "No hay suficientes cupos disponibles para la reserva.");
                return ResponseEntity.badRequest().body(responseJson);
            }

            // Si itodo va bien se actualiza el evento con la cantidad nueva
            evento.setNumReservasActual(reservasActuales + nuevosCupos);
            eventosRepository.save(evento); // Evento Actualizado

            // Guardando Reserva
            registroRepository.save(nuevoRegistro);

            responseJson.put("resultado", "successful");
            responseJson.put("mensaje", "Reserva realizada con éxito.");
            responseJson.put("Id del Registro", nuevoRegistro.getIdRegistro());
            return ResponseEntity.ok(responseJson);

        } catch (Exception e) {
            responseJson.put("resultado", "failure");
            responseJson.put("mensaje", "Ocurrió un error al registrar la reserva.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
        }
    }




    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar el evento o el registro correctamente");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }

}
