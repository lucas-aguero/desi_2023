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

    private final IAeropuertoRepo repo;
    private final ResourceLoader resourceLoader;
    private final AeropuertoMapper mapper;

    @Autowired
    public AeropuertoServiceImpl(IAeropuertoRepo aeropuertoRepo, ResourceLoader resourceLoader, AeropuertoMapper mapper) {
        this.repo = aeropuertoRepo;
        this.resourceLoader = resourceLoader;
        this.mapper = mapper;
    }


    @Override
    public List<AeropuertoDTO> getAeropuertosAleatorios() {

        List<AeropuertoDTO> aeropuertoDTOs =  getAeropuertosArgentinosAleatorios();

        aeropuertoDTOs.addAll(getAeropuertosExtranjerosAleatorios());
        aeropuertoDTOs.addAll(getAeropuertosUsaAleatorios());

        return aeropuertoDTOs;
    }

    public List<AeropuertoDTO> getAeropuertosArgentinosAleatorios(){
        return mapper.aeropuertosToDTOs(repo.getAeropuertosArgentinosAleatorios());
    }

    public List<AeropuertoDTO> getAllAeropuertosArgentinos(){
        return mapper.aeropuertosToDTOs(repo.findAllAeropuertosArgentinos());
    }

    public List<AeropuertoDTO> getAeropuertosExtranjerosAleatorios(){

        List<AeropuertoDTO> aeropuertoDTOs = mapper.aeropuertosToDTOs(repo.getAeropuertosExtranjerosAleatorios());
        aeropuertoDTOs.addAll(getAeropuertosUsaAleatorios());

        return aeropuertoDTOs;
    }

    public List<AeropuertoDTO> getAeropuertosUsaAleatorios(){
        return mapper.aeropuertosToDTOs(repo.getAeropuertosUsaAleatorios());
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

            repo.saveAll(aeropuertosMap.values());

        } catch (Exception e){
            //Personalizar error
            e.printStackTrace();

        }
    }
}
