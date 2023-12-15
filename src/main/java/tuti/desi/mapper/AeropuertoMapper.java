package tuti.desi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.entidades.Aeropuerto;

import java.util.List;
import java.util.TreeSet;

@Mapper(componentModel="spring")
public interface AeropuertoMapper {

    @Mapping(target="iata",ignore=true)
    @Mapping(target="state",ignore=true)
    @Mapping(target="elevation",ignore=true)
    @Mapping(target="lat",ignore=true)
    @Mapping(target="lon",ignore=true)
    @Mapping(target="tz",ignore=true)
    @Mapping(target="vuelosOrigen",ignore=true)
    @Mapping(target="vuelosDestino",ignore=true)
    Aeropuerto aeropuertoDTOToAeropuerto(AeropuertoDTO aeropuerto);

    AeropuertoDTO aeropuertoToDTO(Aeropuerto aeropuerto);
    TreeSet<AeropuertoDTO> aeropuertosToDTOs(List<Aeropuerto> aeropuertos);
    TreeSet<Aeropuerto> aeropuertoDTOsToAeropuertos(List<AeropuertoDTO> aeropuertos);

}
