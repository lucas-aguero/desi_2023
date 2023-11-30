package tuti.desi.presentacion.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.LazyContextVariable;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.dto.AeronaveDTO;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.servicios.AerolineaService;
import tuti.desi.servicios.IAeronaveService;
import tuti.desi.servicios.IAeropuertoService;
import tuti.desi.servicios.VueloService;

import java.time.LocalDate;
import java.util.List;

@Validated
@Controller
@RequestMapping()
public class VueloController {
    private final VueloService vueloService;
    private final AerolineaService aerolineaService;
    private final IAeropuertoService aeropuertoService;
    private final IAeronaveService aeronaveService;

    @Autowired
    public VueloController(VueloService vueloService, AerolineaService aerolineaService,
                           IAeropuertoService aeropuertoService, IAeronaveService aeronaveService) {
        this.vueloService = vueloService;
        this.aerolineaService = aerolineaService;
        this.aeropuertoService = aeropuertoService;
        this.aeronaveService = aeronaveService;
    }

    @GetMapping("/crearVuelo")
    public String prepararVueloForm(Model model){

        NuevoVueloForm form = new NuevoVueloForm();
        List<AerolineaDTO> aerolineas = aerolineaService.getAerolineas();
        List<AeronaveDTO> aeronaves = aeronaveService.getAeronaves();
        List<AeropuertoDTO> aeropuertos = aeropuertoService.getAeropuertosAleatorios();

        model.addAttribute("formBean", form);
        model.addAttribute("aeropuertos", aeropuertos);
        model.addAttribute("aerolineas", aerolineas);
        model.addAttribute("aeronaves", aeronaves);

        return "crearVuelo";
    }

    @PostMapping("/crearVuelo")
    public String submit(@ModelAttribute("formBean")@Valid NuevoVueloForm form,
                         ModelMap model,
                         BindingResult binding){

//        List<AerolineaDTO> aerolineas = aerolineaService.getAerolineas();
//        List<AeropuertoDTO> aeropuertos = aeropuertoService.getAeropuertos();
//
//
//        model.addAttribute("aerolineas", aerolineas);

        model.addAttribute("formBean", form);

        return("crearVuelo");
    }

    @ModelAttribute("today")
    public LocalDate today(){
        return LocalDate.now();
    }

    @ModelAttribute("maxDate")
    public LocalDate maxDate(){
        return LocalDate.now().plusDays(365);
    }

    @ModelAttribute("aeropuertoLocal")
    public AeropuertoDTO aeropuertoLocal(){
        return aeropuertoService.getAeropuertoLocal();
    }
}