package tuti.desi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuti.desi.dto.AeronaveDTO;
import tuti.desi.entidades.Aeronave;

import java.util.List;
import java.util.TreeSet;

@Mapper(componentModel="spring")
public interface AeronaveMapper {


    AeronaveDTO aeronaveToDTO(Aeronave aeronave);

    @InheritInverseConfiguration
    @Mapping(target = "vuelosAeronave", ignore = true)
    Aeronave dtoToAeronave(AeronaveDTO dto);

    TreeSet<AeronaveDTO> aeronavesToAeronaveDTOs(List<Aeronave> aeronaves);
    TreeSet<Aeronave> aeronavesDTOsToAeronaves(List<AeronaveDTO> dtos);
}
