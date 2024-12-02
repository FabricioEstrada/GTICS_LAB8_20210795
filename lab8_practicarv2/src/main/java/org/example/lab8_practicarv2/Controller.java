package org.example.lab8_practicarv2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/eventos")
public class Controller {
    final eventosRepository EventoRepository;
    final registroRepository RegistroRepository;
    public Controller(eventosRepository eventoRepository, org.example.lab8_practicarv2.registroRepository registroRepository) {
        this.EventoRepository = eventoRepository;
        this.RegistroRepository = registroRepository;
    }
    @GetMapping(value = "/list")
    public ResponseEntity<HashMap<String, Object>> listarEventos(){
        HashMap<String,Object> responseJson = new HashMap<>();
        try{
            ArrayList<eventos> listaEventoz= (ArrayList<eventos>) EventoRepository.findAll();
            responseJson.put("result","success");
            responseJson.put("listaEventos",listaEventoz);
            return ResponseEntity.ok(responseJson);
        }catch (Exception e){
            responseJson.put("msg","Algo pasó!" + e.getMessage());
        }
        responseJson.put("result","failure");
        return ResponseEntity.badRequest().body(responseJson);
    }

    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<HashMap<String,Object>> buscarporID(@PathVariable String id){
        HashMap<String,Object> responseJson = new HashMap<>();
        try{
            Optional<eventos> OptEvento = EventoRepository.findById(Integer.valueOf(id));
            if (OptEvento.isPresent()){
                responseJson.put("result","success");
                responseJson.put("evento",OptEvento.get());
                return ResponseEntity.ok(responseJson);
            }
        } catch (NumberFormatException e){
            responseJson.put("msg","producto no encontrado");
        }
        responseJson.put("result","failure");
        return ResponseEntity.badRequest().body(responseJson);
    }

    @PostMapping("/crear")
    public ResponseEntity<HashMap<String, Object>> crearEvento(@RequestBody eventos Evento){
        HashMap<String,Object> responseJson = new HashMap<>();
        try{
            if(Evento.getCategoria()==null || Evento.getFecha()==null ||
            Evento.getNombre()==null || Evento.getNumReservasActual()==null || Evento.getCapacidadMax()==null){
                responseJson.put("msg","alguno de los parámetros es nulo");
                return ResponseEntity.badRequest().body(responseJson);
            }
            eventos creadoEvento=EventoRepository.save(Evento);
            responseJson.put("result","success");
            responseJson.put("msg","Fue creado corretamente con el id" + creadoEvento.getIdEventos());
            return ResponseEntity.ok(responseJson);
        }catch (Exception e){
            responseJson.put("msg",e.getMessage());
        }
        responseJson.put("result","failure");
        return ResponseEntity.badRequest().body(responseJson);
    }
}
