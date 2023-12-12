package tuti.desi.util.faker;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.entidades.Aeronave;
import tuti.desi.excepciones.aeronaveexception.AeronaveNoCreadaException;

@Component
public class AeronaveCreator {
    private final Faker faker;
    private final IAeronaveRepo repo;

    @Autowired
    public AeronaveCreator(Faker faker, IAeronaveRepo repo) {
        this.faker = faker;
        this.repo = repo;
    }

    public Aeronave createAeronave(){

        return new Aeronave(
                faker.aviation().airplane(),
                faker.number().numberBetween(5,10),
                faker.number().numberBetween(10,30));

    }
    public String persistAeronaves(){
        String modeloAeronave;
        String statusMessage = "Inicialización de registros de aeronaves exitosa.<br>" +
                "\nNro de registros creados: ";

        for (int i = 0; i < 112 ; i++) {
            //contar limite de aeronaves
            try{
                if(repo.count() >= 90){

                    return statusMessage.concat(Long.toString(repo.count()));

                }

                Aeronave aeronave = createAeronave();
                modeloAeronave = aeronave.getModelo();

                if(repo.existsByModelo(modeloAeronave) && i > 0){
                    i--;

                } else if (!repo.existsByModelo(modeloAeronave)){
                    repo.save(aeronave);
                }

            }catch(Exception e){
                throw new AeronaveNoCreadaException("Error Interno, No se pudieron crear los registros.<br>" +
                        "Comuníquese con un administrador del sistema.");
            }

        }
        return statusMessage.concat(Long.toString(repo.count()));
    }
    
    public String persistAeronaves(int cant){
        
        String modeloAeronave;
        String statusMessage = "Inicialización de registros de aeronaves exitosa.<br>" +
                "\nNro de registros creados: ";

        for (int i = 0; i < cant ; i++) {
            //contar limite de aeronaves
            try{
                if(repo.count() >= 90){

                    return statusMessage.concat(Long.toString(repo.count()));

                }

                Aeronave aeronave = createAeronave();
                modeloAeronave = aeronave.getModelo();

                if(repo.existsByModelo(modeloAeronave) && i > 0){
                    i--;

                } else if (!repo.existsByModelo(modeloAeronave)){
                    repo.save(aeronave);
                }

            }catch(Exception e){
                throw new AeronaveNoCreadaException("Error Interno, No se pudo crear la aeronave.<br>" +
                        "Comuníquese con un administrador del sistema.");
            }

        }
        return statusMessage.concat(Long.toString(repo.count()));
    }

}
