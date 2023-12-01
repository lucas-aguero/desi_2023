package tuti.desi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.entidades.Aerolinea;

import java.util.List;
import java.util.TreeSet;

@Mapper(componentModel="spring")
public interface AerolineaMapper {

    AerolineaDTO aerolineaToDTO(Aerolinea aerolinea);
    @InheritInverseConfiguration
    @Mapping(target = "vuelosAerolinea", ignore = true)
    Aerolinea aerolineaDTOToAerolinea(AerolineaDTO dto);
    TreeSet<AerolineaDTO> aerolineasToDTOs(List<Aerolinea> aerolineas);
    TreeSet<Aerolinea> aerolineaDTOsToAerolineas(List<AerolineaDTO> aerolineaDTOs);


}
