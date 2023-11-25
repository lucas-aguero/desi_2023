package tuti.desi.presentacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tuti.desi.presentacion.form.VueloForm;
import tuti.desi.servicios.VueloService;

@Validated
@Controller
@RequestMapping()
public class VueloController {
    private final VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

//    @PostMapping("/vuelos")
//    public ResponseEntity<?> crearVuelo(@RequestBody VueloDTO dto, HttpServletRequest request){
//
//        try{
//            VueloDTO nuevoVueloDTO = vueloService.crearVuelo(dto);
//
//            return new ResponseEntity<>(nuevoVueloDTO, HttpStatus.OK);
//
//        }catch (VueloNoCreadoException e){
//
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
//    }

    @GetMapping("/crearVuelo")
    public String crearVuelo(){

        return "crearVuelo";
    }
    @PostMapping("/crearVuelo")
    public String submit(@ModelAttribute VueloForm vueloDTO){

        return("crearVuelo");
    }

}
