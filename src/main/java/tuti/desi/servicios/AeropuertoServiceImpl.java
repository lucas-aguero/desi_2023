package tuti.desi.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.entidades.Aeropuerto;
import tuti.desi.excepciones.aeropuertoexception.AeropuertoNoEncontradoException;
import tuti.desi.excepciones.vueloexception.VueloNoEncontradoException;
import tuti.desi.mapper.AeropuertoMapper;

import java.io.InputStream;
import java.util.*;

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
            throw new AeropuertoNoEncontradoException();
        }


    }
    @Override
    public TreeSet<AeropuertoDTO> getAllAeropuertos(){
        return mapper.aeropuertosToDTOs(repo.findAllAeropuertos());
    }

    @Override
    public TreeSet<AeropuertoDTO> getAeropuertosAleatorios() {

        TreeSet<AeropuertoDTO> aeropuertoDTOs = new TreeSet<AeropuertoDTO>(getAeropuertosArgentinosAleatorios());

        aeropuertoDTOs.addAll(getAeropuertosExtranjerosAleatorios());
        aeropuertoDTOs.addAll(getAeropuertosUsaAleatorios());


        return aeropuertoDTOs;
    }

    @Override
    public TreeSet<AeropuertoDTO> getAeropuertosArgentinosAleatorios(){
        return mapper.aeropuertosToDTOs(repo.getAeropuertosArgentinosAleatorios());
    }

    @Override
    public TreeSet<AeropuertoDTO> getAllAeropuertosArgentinos(){
        return mapper.aeropuertosToDTOs(repo.findAllAeropuertosArgentinos());
    }

    @Override
    public TreeSet<AeropuertoDTO> getAeropuertosExtranjerosAleatorios(){

        TreeSet<AeropuertoDTO> aeropuertoDTOs = mapper.aeropuertosToDTOs(repo.getAeropuertosExtranjerosAleatorios());
        aeropuertoDTOs.addAll(getAeropuertosUsaAleatorios());

        return aeropuertoDTOs;
    }

    @Override
    public TreeSet<AeropuertoDTO> getAeropuertosUsaAleatorios(){
        return mapper.aeropuertosToDTOs(repo.getAeropuertosUsaAleatorios());
    }

    public int contarAeropuertos(){
        return repo.contarAeropuertos();
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
