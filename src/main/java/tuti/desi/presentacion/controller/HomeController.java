package tuti.desi.presentacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.excepciones.aerolineaexception.AerolineaNoCreadaException;
import tuti.desi.excepciones.aeronaveexception.AeronaveNoCreadaException;
import tuti.desi.servicios.AerolineaServiceImpl;
import tuti.desi.servicios.AeronaveServiceImpl;
import tuti.desi.servicios.AeropuertoServiceImpl;
import tuti.desi.servicios.VueloServiceImpl;
import tuti.desi.util.faker.AerolineaCreator;
import tuti.desi.util.faker.AeronaveCreator;
import tuti.desi.util.faker.VueloCreator;

@Controller
@RequestMapping("/")
public class HomeController {

    private final AeropuertoServiceImpl aeropuertoService;
    private final AerolineaServiceImpl aerolineaService;
    private final AeronaveServiceImpl aeronaveService;
    private final VueloServiceImpl vueloService;
    private final AeronaveCreator aeronaveCreator;
    private final AerolineaCreator aerolineaCreator;
    private final VueloCreator vueloCreator;


    @Autowired
    public HomeController(AeropuertoServiceImpl aeropuertoService, AeronaveCreator aeronaveCreator, AerolineaCreator aerolineaCreator, VueloCreator vueloCreator, IAeropuertoRepo aeropuertoRepo, AerolineaServiceImpl aerolineaService, AeronaveServiceImpl aeronaveService, VueloServiceImpl vueloService) {
        this.aeropuertoService = aeropuertoService;
        this.aeronaveCreator = aeronaveCreator;
        this.aerolineaCreator = aerolineaCreator;
        this.vueloCreator = vueloCreator;
        this.aerolineaService = aerolineaService;
        this.aeronaveService = aeronaveService;
        this.vueloService = vueloService;
    }

    @GetMapping("/inicializar-datos")
    public String inicializarDatos(ModelMap model,
                                   RedirectAttributes redirectAttributes){
        String statusMessage="Inicialización de datos exitosa.<br>";
        try{

            aerolineaCreator.persistAerolineas();
            statusMessage = statusMessage.concat("Aerolíneas: "
                    + Long.toString(aerolineaService.contarAerolineas())
                    + "<br>");
            aeronaveCreator.persistAeronaves();
            statusMessage = statusMessage.concat("Aeronaves: "
                    + Long.toString(aeronaveService.contarAeronaves())
                    + "<br>");

            if(aeropuertoService.contarAeropuertos() == 0){
                aeropuertoService.loadAirportsFromJsonFile();
            }
            statusMessage = statusMessage.concat("Aeropuertos: "
                    + Long.toString(aeropuertoService.contarAeropuertos())
                    + "<br>");

            vueloCreator.persistirLotesVuelos(4);
            statusMessage = statusMessage.concat("Vuelos: "
                    + Long.toString(vueloService.contarAeropuertos()));

            redirectAttributes.addFlashAttribute("statusMessage", statusMessage);

        }catch(Exception e){
            String errorMessage  = "Error Interno, No se pudieron crear los registros.<br>" +
                    "Comuníquese con un administrador del sistema.";

            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        }
        redirectAttributes.addFlashAttribute("messageTitle", "Creación de Aeronaves");
        redirectAttributes.addFlashAttribute("toastId", "toastId");
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
    public String inicializarAerolineas(ModelMap model,
                                        RedirectAttributes redirectAttributes){

        try {
            String statusMessage = aerolineaCreator.persistAerolineas();
            redirectAttributes.addFlashAttribute("statusMessage", statusMessage);

        } catch (AerolineaNoCreadaException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        redirectAttributes.addFlashAttribute("messageTitle", "Creación de Aerolíneas");
        redirectAttributes.addFlashAttribute("toastId", "toastId");

        return "redirect:/";
    }

    @GetMapping("/inicializar-aeropuertos")
    public String inicializarAeropuertos(ModelMap model,
                                         RedirectAttributes redirectAttributes){

        String statusMessage = "Inicialización de registros de aeropuertos exitosa.<br>" +
                "Nro de registros creados: ";

        try{
            if(aeropuertoService.contarAeropuertos() == 0){

                aeropuertoService.loadAirportsFromJsonFile();

                redirectAttributes.addFlashAttribute("statusMessage",
                        statusMessage.concat(Long.toString(aeropuertoService.contarAeropuertos())));

            }else {
                redirectAttributes.addFlashAttribute("statusMessage",
                        statusMessage.concat(Long.toString( + aeropuertoService.contarAeropuertos())));
            }

        }catch(Exception e){

            String errorMessage  = "Error Interno, No se pudieron crear los registros.<br>" +
                    "Comuníquese con un administrador del sistema.";

            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        }

        redirectAttributes.addFlashAttribute("messageTitle", "Inicialización de Datos Demo");
        redirectAttributes.addFlashAttribute("toastId", "toastId");

        return "redirect:/";
    }

    @GetMapping("/inicializar-vuelos")
    public String inicializarVuelos(ModelMap model,
                                    RedirectAttributes redirectAttributes){

        try{
            if(aeropuertoService.contarAeropuertos() == 0){
                redirectAttributes.addFlashAttribute("errorMessage", "Debe inicializar datos de " +
                        "aeropuertos antes de crear vuelos");

            }else{
                //CANT * 4 = 16
                String statusMessage = vueloCreator.persistirLotesVuelos(4);
                redirectAttributes.addFlashAttribute("statusMessage", statusMessage);
            }

        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Error Interno, No se pudieron crear los registros.<br>" +
                    "Comuníquese con un administrador del sistema.");
        }

        redirectAttributes.addFlashAttribute("messageTitle", "Creación de Vuelos");
        redirectAttributes.addFlashAttribute("toastId", "toastId");

        return "redirect:/";
    }

}
