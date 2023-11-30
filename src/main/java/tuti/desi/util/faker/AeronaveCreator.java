package tuti.desi.util.faker;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.entidades.Aeronave;

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

        //        if(!repo.existsByModelo(aeronave.getModelo()))
    //            repo.save(aeronave);
        return new Aeronave(
                faker.aviation().airplane(),
                faker.number().numberBetween(5,10),
                faker.number().numberBetween(10,30));

    }
    public void persistAeronaves(){
        String modeloAeronave;

        for (int i = 0; i < 10 ; i++) {
            Aeronave aeronave = createAeronave();
            modeloAeronave = aeronave.getModelo();

            if(repo.existsByModelo(modeloAeronave) && i > 0){
                i--;

            } else if (!repo.existsByModelo(modeloAeronave)){
                repo.save(aeronave);
            }
        }

    }
    public void persistAeronaves(int cant){
        String modeloAeronave;

        for (int i = 0; i < cant ; i++) {
            Aeronave aeronave = createAeronave();
            modeloAeronave = aeronave.getModelo();

            if(repo.existsByModelo(modeloAeronave) && i > 0){
                i--;

            } else if (!repo.existsByModelo(modeloAeronave)){
                repo.save(aeronave);
            }
        }

    }

}
