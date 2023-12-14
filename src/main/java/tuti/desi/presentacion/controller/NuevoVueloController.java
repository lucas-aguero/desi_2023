package tuti.desi.presentacion.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.dto.AeronaveDTO;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.entidades.Vuelo;
import tuti.desi.excepciones.vueloexception.VueloPersistenceException;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.servicios.AerolineaService;
import tuti.desi.servicios.IAeronaveService;
import tuti.desi.servicios.IAeropuertoService;
import tuti.desi.servicios.VueloService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @GetMapping("/lista-vuelos")
    public String listarVuelos(Model model){
        List<Vuelo> vuelos = vueloService.getVuelos();
        model.addAttribute("vuelos", vuelos);



        return "lista-vuelos";
    }


    @GetMapping("/crearVuelo")
    public String prepararVueloForm(Model model){

        NuevoVueloForm formBean = new NuevoVueloForm();
        model.addAttribute("formBean", formBean);

        return "vuelos/crearVuelo";
    }

    @PostMapping("/crearVuelo")
    public String submit(@Valid @ModelAttribute("formBean")  NuevoVueloForm formBean,
                         BindingResult result,
                         ModelMap model,
                         RedirectAttributes redirectAttributes){


        if(result.hasErrors()){

            model.addAttribute("formBean", formBean);
            return "vuelos/crearVuelo";
        }


        try{

            vueloService.crearVuelo(formBean);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Vuelo creado con éxito!");

            return("redirect:/crearVuelo");

        }catch (VueloPersistenceException e){

            ObjectError error = new ObjectError("global", e.getMessage());

            model.addAttribute("global", error);
            return("vuelos/crearVuelo");
        }

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

    @ModelAttribute("aeropuertosInternac")
    public TreeSet<AeropuertoDTO> aeropuertosInternac(){
        return aeropuertoService.getAeropuertosExtranjerosAleatorios();
    }

    @ModelAttribute("aeropuertosNac")
    public TreeSet<AeropuertoDTO> aeropuertosNac(){
        return aeropuertoService.getAllAeropuertosArgentinos();
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