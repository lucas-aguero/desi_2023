package tuti.desi.presentacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.excepciones.aeronaveexception.AeronaveNoCreadaException;
import tuti.desi.servicios.AerolineaServiceImpl;
import tuti.desi.servicios.AeronaveServiceImpl;
import tuti.desi.servicios.AeropuertoServiceImpl;
import tuti.desi.util.faker.AerolineaCreator;
import tuti.desi.util.faker.AeronaveCreator;
import tuti.desi.util.faker.VueloCreator;

@Controller
@RequestMapping("/")
public class HomeController {

    private final AeropuertoServiceImpl aeropuertoService;
    private final AerolineaServiceImpl aerolineaService;
    private final AeronaveServiceImpl aeronaveService;
    private final AeronaveCreator aeronaveCreator;
    private final AerolineaCreator aerolineaCreator;
    private final VueloCreator vueloCreator;


    @Autowired
    public HomeController(AeropuertoServiceImpl aeropuertoService, AeronaveCreator aeronaveCreator, AerolineaCreator aerolineaCreator, VueloCreator vueloCreator, IAeropuertoRepo aeropuertoRepo, AerolineaServiceImpl aerolineaService, AeronaveServiceImpl aeronaveService) {
        this.aeropuertoService = aeropuertoService;
        this.aeronaveCreator = aeronaveCreator;
        this.aerolineaCreator = aerolineaCreator;
        this.vueloCreator = vueloCreator;
        this.aerolineaService = aerolineaService;
        this.aeronaveService = aeronaveService;
    }

    @GetMapping("/inicializar-datos")
    public String inicializarDatos(){

        aerolineaCreator.persistAerolineas();
        aeronaveCreator.persistAeronaves();

        if(aeropuertoService.contarAeropuertos() == 0){
            aeropuertoService.loadAirportsFromJsonFile();
        }

        vueloCreator.persistirLotesVuelos(4);

        return "redirect:/";
    }

    @GetMapping("/inicializar-aeronaves")
    public String inicializarAeronaves(ModelMap model,
                                       RedirectAttributes redirectAttributes){

        try{
            String statusMessage = aeronaveCreator.persistAeronaves();

            redirectAttributes.addFlashAttribute("statusMessage", statusMessage);


        }catch(AeronaveNoCreadaException e){

            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        }

        redirectAttributes.addFlashAttribute("messageTitle", "Creación de Aeronaves");
        redirectAttributes.addFlashAttribute("toastId", "toastId");

        return "redirect:/";
    }

    @GetMapping("/inicializar-aerolineas")
    public String inicializarAerolineas(){

        aerolineaCreator.persistAerolineas();

        return "redirect:/";
    }

    @GetMapping("/inicializar-aeropuertos")
    public String inicializarAeropuertos(){

        if(aeropuertoService.contarAeropuertos() == 0){
            aeropuertoService.loadAirportsFromJsonFile();
        }else{
            System.out.println("Los aeropuertos ya han sido cargados en el sistema.");
        }
        return "redirect:/";
    }

    @GetMapping("/inicializar-vuelos")
    public String inicializarVuelos(){

        //CANT * 4 = 16
        vueloCreator.persistirLotesVuelos(4);

        return "redirect:/";
    }

}
