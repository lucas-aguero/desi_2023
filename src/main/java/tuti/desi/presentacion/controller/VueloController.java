package tuti.desi.presentacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.servicios.AerolineaService;
import tuti.desi.servicios.VueloService;

@Validated
@Controller
@RequestMapping()
public class VueloController {
    private final VueloService vueloService;
    private final AerolineaService aerolineaService;

    @Autowired
    public VueloController(VueloService vueloService, AerolineaService aerolineaService) {
        this.vueloService = vueloService;
        this.aerolineaService = aerolineaService;
    }

    @GetMapping("/crearVuelo")
    public String prepararVueloForm(){

        return "crearVuelo";
    }

    @PostMapping("/crearVuelo")
    public String submit(@ModelAttribute NuevoVueloForm form){
        NuevoVueloForm dto;

        //List<AerolineaDTO> aerolineas = aerolineaService.getAerolineas();


        //model.addAttribute("dto",dto);
        //model.addAttribute("aeronaves", aeronaves);

        return("crearVuelo");
    }

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