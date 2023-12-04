package tuti.desi.presentacion.controller;

//import jakarta.validation.Valid;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.dto.AeronaveDTO;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.excepciones.vueloexception.VueloPersistenceException;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.servicios.AerolineaService;
import tuti.desi.servicios.IAeronaveService;
import tuti.desi.servicios.IAeropuertoService;
import tuti.desi.servicios.VueloService;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

@Validated
@Controller
@RequestMapping()
public class NuevoVueloController {
    private final VueloService vueloService;
    private final AerolineaService aerolineaService;
    private final IAeropuertoService aeropuertoService;
    private final IAeronaveService aeronaveService;

    @Autowired
    public NuevoVueloController(VueloService vueloService, AerolineaService aerolineaService,
                                IAeropuertoService aeropuertoService, IAeronaveService aeronaveService) {
        this.vueloService = vueloService;
        this.aerolineaService = aerolineaService;
        this.aeropuertoService = aeropuertoService;
        this.aeronaveService = aeronaveService;
    }

    @GetMapping("/crearVuelo")
    public String prepararVueloForm(Model model){

        NuevoVueloForm formBean = new NuevoVueloForm();
        model.addAttribute("formBean", formBean);

        return "crearVuelo";
    }

    @PostMapping("/crearVuelo")
    public String submit(@Valid @ModelAttribute("formBean")  NuevoVueloForm formBean,
                         BindingResult result,
                         ModelMap model){


        if(result.hasErrors()){

            //System.out.println("ENTRO AL HAS ERRORS");
            model.addAttribute("formBean", formBean);
            return "crearVuelo";
        }


        try{

            vueloService.crearVuelo(formBean);

        }catch (VueloPersistenceException e){

            ObjectError error = new ObjectError("global", e.getMessage());

            model.addAttribute("formBean", formBean);
            model.addAttribute("global", error);
            return("/crearVuelo");
        }

        return("redirect:/crearVuelo");
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

    @ModelAttribute("aeropuertos")
    public TreeSet<AeropuertoDTO> aeropuertos(){
        return aeropuertoService.getAeropuertosAleatorios();
    }

    @ModelAttribute("aerolineas")
    public TreeSet<AerolineaDTO> aerolineas(){
        return aerolineaService.getAerolineas();
    }

    @ModelAttribute("aeronaves")
    public TreeSet<AeronaveDTO> aeronaves(){
        return aeronaveService.getAeronaves();
    }

}