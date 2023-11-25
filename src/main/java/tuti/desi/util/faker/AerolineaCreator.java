package tuti.desi.util.faker;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import tuti.desi.accesoDatos.IAerolineaRepo;
import tuti.desi.entidades.Aerolinea;

@Component
public class AerolineaCreator {

    private final Faker faker;
    private final IAerolineaRepo repo;


    public AerolineaCreator(Faker faker, IAerolineaRepo repo) {
        this.faker = faker;
        this.repo = repo;
    }

//    private void createAerolinea() {
////        Aerolinea = Aerolinea.builder()
////                .nombre(faker.aviation().airline()).build();
//    }

    public void createAerolineas(){

        for (int i = 0; i < 10 ; i++) {
            createAerolinea();
        }

    }

    public void createAerolineas(){

        for (int i = 0; i < cant ; i++) {
            createAerolinea();
        }

    }
}
