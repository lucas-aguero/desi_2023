package tuti.desi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuti.desi.dto.VueloDTO;
import tuti.desi.entidades.Vuelo;

import java.util.List;

@Mapper(componentModel="spring")
public interface VueloMapper {
    @Mapping(target="origenId",source="origen.id")
    @Mapping(target="destinoId", source="destino.id")
    @Mapping(target="estadoVuelo", ignore = true)
    @Mapping(target = "precio", numberFormat = "#,##E0")
    @Mapping(target="fechaPartida", dateFormat = "dd/MM/yyyy")
    VueloDTO vueloToVueloDTO(Vuelo vuelo);
    @Mapping(target="nroVuelo", ignore = true)
    @Mapping(target="tipoVuelo", ignore = true)
    @Mapping(target="estadoVuelo", ignore = true)
    @Mapping(target="origen.id",source="origenId")
    @Mapping(target="destino.id", source="destinoId")
//    @Mapping(target="precio", expression="java(new java.math.BigDecimal(source.precio))")
    Vuelo vueloDTOToVuelo(VueloDTO vueloDTO);
    List<VueloDTO> vuelosToVueloDTOs(List<Vuelo> vuelos);
    @Mapping(target="id", ignore = true)
    List<Vuelo> vuelosDTOToVueloDTOs(List<VueloDTO> vueloDTOs);
}
