package tuti.desi.util.faker;

import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import tuti.desi.accesoDatos.IAerolineaRepo;
import tuti.desi.entidades.Aerolinea;
import tuti.desi.excepciones.aerolineaexception.AerolineaNoCreadaException;

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

    public String persistAerolineas(){

        String nombreAerolinea;
        String statusMessage = "Inicialización de registros de aerolíneas exitosa. " +
                "\nNro de registros creados: ";


        for (int i = 0; i < 200 ; i++) {

            try{
                if(repo.count() > 200){
                    return statusMessage.concat(Long.toString(repo.count()));
                }
                Aerolinea aerolinea = createAerolinea();
                nombreAerolinea = aerolinea.getNombre();

                if(repo.existsByNombre(nombreAerolinea) && i > 0){
                    i--;

                } else if (!repo.existsByNombre(nombreAerolinea)){
                    repo.save(aerolinea);
                }

            }catch(Exception e){

                throw new AerolineaNoCreadaException("Error Interno, No se pudieron crear los registros. " +
                        "Comuníquese con un administrador del sistema.");

            }

        }
        return statusMessage.concat(Long.toString(repo.count()));
    }

    public String persistAerolineas(int cant){
        String nombreAerolinea;
        String statusMessage = "Inicialización de registros de aerolíneas exitosa. " +
                "\nNro de registros creados: ";


        for (int i = 0; i < cant ; i++) {

            try{
                if(repo.count() > 200){
                    return statusMessage.concat(Long.toString(repo.count()));
                }
                Aerolinea aerolinea = createAerolinea();
                nombreAerolinea = aerolinea.getNombre();

                if(repo.existsByNombre(nombreAerolinea) && i > 0){
                    i--;

                } else if (!repo.existsByNombre(nombreAerolinea)){
                    repo.save(aerolinea);
                }

            }catch(Exception e){

                throw new AerolineaNoCreadaException("Error Interno, No se pudieron crear los registros. " +
                        "Comuníquese con un administrador del sistema.");

            }

        }
        return statusMessage.concat(Long.toString(repo.count()));

    }
}
