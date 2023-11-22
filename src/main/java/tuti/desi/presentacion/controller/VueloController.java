package tuti.desi.presentacion.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tuti.desi.dto.VueloDTO;
import tuti.desi.excepciones.CrearVueloException;
import tuti.desi.servicios.VueloService;
import tuti.desi.servicios.VueloServiceImpl;

@Validated
@RestController
public class VueloController {
    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping("/vuelos")
    public ResponseEntity<VueloDTO> crearVuelo(@RequestBody VueloDTO dto, HttpServletRequest request){

        try{
            VueloDTO nuevoVueloDTO = vueloService.crearVuelo(dto);

            return new ResponseEntity<>(nuevoVueloDTO, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
