package tuti.desi.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.entidades.Aeropuerto;

import java.io.InputStream;
import java.util.Map;

public class AeropuertoServiceImpl implements IAeropuertoService{

    private final IAeropuertoRepo aeropuertoRepo;
    private final ResourceLoader resourceLoader;

    public AeropuertoServiceImpl(IAeropuertoRepo aeropuertoRepo, ResourceLoader resourceLoader) {
        this.aeropuertoRepo = aeropuertoRepo;
        this.resourceLoader = resourceLoader;
    }


    @Override
    public void loadAirportsFromJsonFile(String jsonFilePath) {
        var objectMapper = new ObjectMapper();

        try {
            Resource resource = resourceLoader.getResource("classpath:data/airports.json");
            InputStream inputStream = resource.getInputStream();

            Map<String, Aeropuerto> aeropuertosMap = objectMapper.readValue(
                    inputStream,
                    objectMapper
                            .getTypeFactory()
                            .constructMapType(Map.class, String.class, Aeropuerto.class)
            );

            aeropuertoRepo.saveAll(aeropuertosMap.values());

        } catch (Exception e){
            //Personalizar error
            e.printStackTrace();

        }
    }
}
