package tuti.desi.mapper;

import org.mapstruct.Mapper;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.entidades.Aeropuerto;

import java.util.List;

@Mapper(componentModel="spring")
public interface AeropuertoMapper {

    AeropuertoDTO aeropuertoToDTO(Aeropuerto aeropuerto);
    Aeropuerto aeropuertoDTOToAeropuerto(AeropuertoDTO aeropuerto);

    List<AeropuertoDTO> aeropuertosToDTOs(List<Aeropuerto> aeropuertos);
    List<Aeropuerto> aeropuertoDTOsToAeropuertos(List<AeropuertoDTO> aeropuertos);

}
