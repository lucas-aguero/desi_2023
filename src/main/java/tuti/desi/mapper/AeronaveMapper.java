package tuti.desi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuti.desi.dto.AeronaveDTO;
import tuti.desi.entidades.Aeronave;

import java.util.List;

@Mapper(componentModel="spring")
public interface AeronaveMapper {


    AeronaveDTO aeronaveToDTO(Aeronave aeronave);

    @InheritInverseConfiguration
    @Mapping(target = "vuelosAeronave", ignore = true)
    Aeronave dtoToAeronave(AeronaveDTO dto);

    List<Aeronave> aeronavesToAeronaveDTOs(List<Aeronave> aeronaves);
    List<AeronaveDTO> aeronavesDTOsToAeronaves(List<AeronaveDTO> dtos);
}
