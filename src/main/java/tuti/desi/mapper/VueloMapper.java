package tuti.desi.mapper;

import org.mapstruct.Mapper;
import tuti.desi.dto.VueloDTO;
import tuti.desi.entidades.Vuelo;

import java.util.List;

@Mapper(componentModel="spring")
public interface VueloMapper {
    VueloDTO vueloToVueloDTO(Vuelo vuelo);
    Vuelo vueloDTOToVuelo(VueloDTO vueloDTO);
    List<VueloDTO> vuelosToVueloDTOs(List<Vuelo> vuelos);
    List<Vuelo> vuelosDTOToVueloDTOs(List<VueloDTO> vueloDTOs);
}
