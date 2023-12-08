package tuti.desi.presentacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tuti.desi.servicios.AeropuertoServiceImpl;
import tuti.desi.util.faker.AerolineaCreator;
import tuti.desi.util.faker.AeronaveCreator;

@Controller
@RequestMapping("/")
public class HomeController {

    private final AeropuertoServiceImpl aeropuertoService;
    private final AeronaveCreator aeronaveCreator;
    private final AerolineaCreator aerolineaCreator;

    @Autowired
    public HomeController(AeropuertoServiceImpl aeropuertoService, AeronaveCreator aeronaveCreator, AerolineaCreator aerolineaCreator) {
        this.aeropuertoService = aeropuertoService;
        this.aeronaveCreator = aeronaveCreator;
        this.aerolineaCreator = aerolineaCreator;
    }

    @GetMapping("/inicializar-datos")
    public String inicializarDatos(){

        aerolineaCreator.persistAerolineas(50);
        aeronaveCreator.persistAeronaves(50);
        aeropuertoService.loadAirportsFromJsonFile();

        return "index";
    }

    @GetMapping("/crear-aeronaves")
    public String crearAeronaves(){

        aeronaveCreator.persistAeronaves(50);

        return "index";
    }

    @GetMapping("/crear-aerolineas")
    public String crearAerolineas(){

        aeronaveCreator.persistAeronaves(50);

        return "index";
    }

    @GetMapping("/crear-aeropuertos")
    public String inicializarAeropuertos(){

        aeropuertoService.loadAirportsFromJsonFile();

        return "index";
    }

    @GetMapping("/crear-vuelos")
    public String crearVuelos(){

        return "index";
    }


}
