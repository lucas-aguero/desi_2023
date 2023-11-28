package tuti.desi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.entidades.Vuelo;

@Mapper(componentModel="spring")
public interface VueloMapper {
    @Mapping(target="origenId",source="origen.id")
    @Mapping(target="destinoId", source="destino.id")
    @Mapping(target="aerolinea", source="aerolinea.nombre"/*, ignore = true*/)
    @Mapping(target="aeronave", source="aeronave.modelo"/*, ignore = true*/)
    @Mapping(target = "precio", numberFormat = "#,##E0")
    @Mapping(target="fechaPartida", dateFormat = "dd/MM/yyyy")
    NuevoVueloForm vueloToForm(Vuelo vuelo);
//    @Mapping(target="nroVuelo", ignore = true)
//    @Mapping(target="origen.id",source="origenId")
//    @Mapping(target="destino.id", source="destinoId")
//    @Mapping(target="aerolinea.nombre", source="aerolinea")
//    @Mapping(target="aeronave.modelo", source="aeronave")
//    @Mapping(target="tipoVuelo", ignore = true)
//    @Mapping(target="estadoVuelo", ignore = true)
//    @Mapping(target="precio", expression="java(new java.math.BigDecimal(source.precio))")
    @InheritInverseConfiguration
    @Mapping(target="tipoVuelo", ignore = true)
    @Mapping(target="estadoVuelo", ignore = true)
    Vuelo formToVuelo(NuevoVueloForm vueloForm);
//    List<VueloForm> vuelosToVueloDTOs(List<Vuelo> vuelos);
//    @Mapping(target="id", ignore = true)
//    List<Vuelo> vuelosDTOToVueloDTOs(List<VueloForm> vueloDTOs);
}
