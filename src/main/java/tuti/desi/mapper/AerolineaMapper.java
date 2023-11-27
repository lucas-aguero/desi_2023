package tuti.desi.mapper;

import org.mapstruct.Mapper;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.entidades.Aerolinea;

import java.util.List;

@Mapper(componentModel="spring")
public interface AerolineaMapper {
    AerolineaDTO aerolineaToDTO(Aerolinea aerolinea);
    Aerolinea aerolineaDTOToAerolinea(AerolineaDTO dto);
    List<AerolineaDTO> aerolineasToDTOs(List<Aerolinea> aerolineas);
    List<Aerolinea> aerolineaDTOsToAerolineas(List<AerolineaDTO> aerolineaDTOs);


}
