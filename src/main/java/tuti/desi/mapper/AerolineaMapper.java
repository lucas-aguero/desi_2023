package tuti.desi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.entidades.Aerolinea;

import java.util.List;

@Mapper(componentModel="spring")
public interface AerolineaMapper {

    AerolineaDTO aerolineaToDTO(Aerolinea aerolinea);
    @InheritInverseConfiguration
    @Mapping(target = "vuelosAerolinea", ignore = true)
    Aerolinea aerolineaDTOToAerolinea(AerolineaDTO dto);
    List<AerolineaDTO> aerolineasToDTOs(List<Aerolinea> aerolineas);
    List<Aerolinea> aerolineaDTOsToAerolineas(List<AerolineaDTO> aerolineaDTOs);


}
