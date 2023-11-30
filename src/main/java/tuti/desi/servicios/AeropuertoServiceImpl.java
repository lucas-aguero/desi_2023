package tuti.desi.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.entidades.Aeropuerto;
import tuti.desi.excepciones.VueloNoEncontradoException;
import tuti.desi.mapper.AeropuertoMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public AeropuertoDTO getAeropuertoLocal() throws VueloNoEncontradoException {

        try{
            Optional<Aeropuerto> aeropuertoOptional = repo.findByIcao("SAAV");

            if(aeropuertoOptional.isPresent()){

                return mapper.aeropuertoToDTO(aeropuertoOptional.get());

            } else {

                Aeropuerto aeropuerto = Aeropuerto.builder()
                        .icao("SAAV")
                        .iata("SFN")
                        .name("Aeropuerto Sauce Viejo")
                        .city("Sauce Viejo")
                        .state("Santa Fe")
                        .country("AR")
                        .lat(-31.7117)
                        .lon(-60.8117)
                        .tz("America/Argentina/Cordoba")
                        .build();

                return mapper.aeropuertoToDTO(aeropuerto);
            }

        }catch(Exception e){
            throw new VueloNoEncontradoException();
        }


    }
    @Override
    public List<AeropuertoDTO> getAllAeropuertos(){
        return mapper.aeropuertosToDTOs(repo.findAllAeropuertos());
    }

    @Override
    public List<AeropuertoDTO> getAeropuertosAleatorios() {

        List<AeropuertoDTO> aeropuertoDTOs =  getAeropuertosArgentinosAleatorios();

        aeropuertoDTOs.addAll(getAeropuertosExtranjerosAleatorios());
        aeropuertoDTOs.addAll(getAeropuertosUsaAleatorios());

        return aeropuertoDTOs;
    }

    @Override
    public List<AeropuertoDTO> getAeropuertosArgentinosAleatorios(){
        return mapper.aeropuertosToDTOs(repo.getAeropuertosArgentinosAleatorios());
    }

    @Override
    public List<AeropuertoDTO> getAllAeropuertosArgentinos(){
        return mapper.aeropuertosToDTOs(repo.findAllAeropuertosArgentinos());
    }

    @Override
    public List<AeropuertoDTO> getAeropuertosExtranjerosAleatorios(){

        List<AeropuertoDTO> aeropuertoDTOs = mapper.aeropuertosToDTOs(repo.getAeropuertosExtranjerosAleatorios());
        aeropuertoDTOs.addAll(getAeropuertosUsaAleatorios());

        return aeropuertoDTOs;
    }

    @Override
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
