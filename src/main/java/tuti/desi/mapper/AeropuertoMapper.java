package tuti.desi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.entidades.Aeropuerto;

import java.util.List;

@Mapper(componentModel="spring")
public interface AeropuertoMapper {


    AeropuertoDTO aeropuertoToDTO(Aeropuerto aeropuerto);
    @Mapping(target="id",ignore=true)
    @Mapping(target="icao",ignore=true)
    @Mapping(target="state",ignore=true)
    @Mapping(target="elevation",ignore=true)
    @Mapping(target="lat",ignore=true)
    @Mapping(target="lon",ignore=true)
    @Mapping(target="tz",ignore=true)
    @Mapping(target="vuelosOrigen",ignore=true)
    @Mapping(target="vuelosDestino",ignore=true)
    Aeropuerto aeropuertoDTOToAeropuerto(AeropuertoDTO aeropuerto);

    List<AeropuertoDTO> aeropuertosToDTOs(List<Aeropuerto> aeropuertos);
    List<Aeropuerto> aeropuertoDTOsToAeropuertos(List<AeropuertoDTO> aeropuertos);

}
