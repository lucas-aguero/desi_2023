package tuti.desi.util.inicializador;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.servicios.IAeropuertoService;
import tuti.desi.util.faker.AeronaveCreator;


@Component
public class Migrador {

    private final IAeropuertoService aeropuertoService;
    private final AeronaveCreator aeronaveCreator;

    @Autowired
    public Migrador(Faker faker, IAeronaveRepo repo, IAeropuertoService aeropuertoService, AeronaveCreator aeronaveCreator) {
        this.aeropuertoService = aeropuertoService;
        this.aeronaveCreator = aeronaveCreator;
    }

//Espera a que la app este lista para ejecutar la migracion
    @EventListener(ApplicationReadyEvent.class)
    public void initialize(){
        //aeropuertoService.loadAirportsFromJsonFile();
        aeronaveCreator.createAeronaves(1);
    }

}
