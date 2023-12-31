package tuti.desi.util.inicializador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.servicios.IAeropuertoService;
import tuti.desi.util.faker.AerolineaCreator;
import tuti.desi.util.faker.AeronaveCreator;


@Component
public class Migrador {

    private final IAeropuertoService aeropuertoService;
    private final AeronaveCreator aeronaveCreator;
    private final AerolineaCreator aerolineaCreator;

    @Autowired
    public Migrador(IAeropuertoService aeropuertoService, AeronaveCreator aeronaveCreator, AerolineaCreator aerolineaCreator) {
        this.aeropuertoService = aeropuertoService;
        this.aeronaveCreator = aeronaveCreator;
        this.aerolineaCreator = aerolineaCreator;
    }

//Espera a que la app este lista para ejecutar la migracion
    @EventListener(ApplicationReadyEvent.class)
    public void initialize(){
//        aeropuertoService.loadAirportsFromJsonFile();
//        aeronaveCreator.persistAeronaves(50);
//        aerolineaCreator.persistAerolineas(50);
    }

}
