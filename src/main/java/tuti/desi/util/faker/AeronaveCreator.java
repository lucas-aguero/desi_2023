package tuti.desi.util.faker;


import com.github.javafaker.Faker;
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

    public void createAeronave(){

        Aeronave aeronave = new Aeronave(
                faker.aviation().aircraft(),
                faker.number().numberBetween(5,10),
                faker.number().numberBetween(10,30));

        if(!repo.existsByModelo(aeronave.getModelo()))
            repo.save(aeronave);

    }
    public void createAeronaves(){

        for (int i = 0; i < 10 ; i++) {
            createAeronave();
        }

    }
    public void createAeronaves(int cant){

        for (int i = 0; i < cant ; i++) {
            createAeronave();
        }

    }


}
