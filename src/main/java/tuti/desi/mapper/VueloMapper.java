package tuti.desi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuti.desi.dto.VueloDTO;
import tuti.desi.entidades.Vuelo;

import java.util.List;

@Mapper(componentModel="spring")
public interface VueloMapper {
    VueloDTO vueloToVueloDTO(Vuelo vuelo);
    @Mapping(target="nroVuelo", ignore = true)
    @Mapping(target="origen", ignore = true)
    @Mapping(target="destino", ignore = true)
    Vuelo vueloDTOToVuelo(VueloDTO vueloDTO);
    List<VueloDTO> vuelosToVueloDTOs(List<Vuelo> vuelos);
    @Mapping(target="id", ignore = true)
    List<Vuelo> vuelosDTOToVueloDTOs(List<VueloDTO> vueloDTOs);
}
