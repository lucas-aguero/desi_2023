package tuti.desi.util.faker;

import net.datafaker.Faker;
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

    public Aerolinea createAerolinea() {

        return Aerolinea.builder()
                .nombre(faker.aviation().airline())
                .build();
    }

    public void persistAerolineas(){

        String nombreAerolinea;

        for (int i = 0; i < 200 ; i++) {

            Aerolinea aerolinea = createAerolinea();
            nombreAerolinea = aerolinea.getNombre();

            if(repo.existsByNombre(nombreAerolinea) && i > 0){
                i--;

            } else if (!repo.existsByNombre(nombreAerolinea)){
                repo.save(aerolinea);
            }

            if(repo.count() > 200){
                System.out.println("Ya se han cargado aerolineas en el sistema");
                break;
            }
        }

    }

    public void persistAerolineas(int cant){
        String nombreAerolinea;

        for (int i = 0; i < cant ; i++) {

            Aerolinea aerolinea = createAerolinea();
            nombreAerolinea = aerolinea.getNombre();

            if(repo.existsByNombre(nombreAerolinea) && i > 0){
                i--;

            } else if (!repo.existsByNombre(nombreAerolinea)){
                repo.save(aerolinea);
            }

            if(repo.count() > 200){
                System.out.println("Ya se han cargado aerolineas en el sistema");
                break;
            }
        }

    }
}
