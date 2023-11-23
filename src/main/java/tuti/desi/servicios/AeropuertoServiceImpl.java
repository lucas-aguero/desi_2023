package tuti.desi.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.entidades.Aeropuerto;
import tuti.desi.mapper.AeropuertoMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class AeropuertoServiceImpl implements IAeropuertoService{

    private final IAeropuertoRepo aeropuertoRepo;
    private final ResourceLoader resourceLoader;
    private final AeropuertoMapper mapper;

    @Autowired
    public AeropuertoServiceImpl(IAeropuertoRepo aeropuertoRepo, ResourceLoader resourceLoader, AeropuertoMapper mapper) {
        this.aeropuertoRepo = aeropuertoRepo;
        this.resourceLoader = resourceLoader;
        this.mapper = mapper;
    }


    @Override
    public List<AeropuertoDTO> getAllAeropuertos() {

        List<Aeropuerto> aeropuertos = aeropuertoRepo.findAll();

        return mapper.aeropuertosToDTOs(aeropuertos);
    }

    @Override
    public void loadAirportsFromJsonFile() {
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
