package tuti.desi.util.inicializador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tuti.desi.servicios.IAeropuertoService;

@Component
public class MigradorAeropuertos {

    private final IAeropuertoService aeropuertoService;

    @Autowired
    public MigradorAeropuertos(IAeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    //Espera a que la app este lista para ejecutar la migracion
//    @EventListener(ApplicationReadyEvent.class)
//    public void initialize(){
//        aeropuertoService.loadAirportsFromJsonFile();
//    }

}
