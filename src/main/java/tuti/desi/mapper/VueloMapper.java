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
    @Mapping(target="aerolineaId", source="aerolinea.id"/*, ignore = true*/)
    @Mapping(target="aeronaveId", source="aeronave.id"/*, ignore = true*/)
    @Mapping(target = "precioNeto", numberFormat = "#,##E0")
    @Mapping(target="fechaPartida", dateFormat = "dd-MM-yyyy")
    NuevoVueloForm vueloToForm(Vuelo vuelo);
    @InheritInverseConfiguration
    @Mapping(target="nroVuelo", ignore = true)
    @Mapping(target="estadoVuelo", ignore = true)
    @Mapping(target="tipoVuelo", ignore = true)
    @Mapping(target="fechaPartida", dateFormat = "yyyy-MM-dd")
    Vuelo formToVuelo(NuevoVueloForm vueloForm);
//    List<VueloForm> vuelosToVueloDTOs(List<Vuelo> vuelos);
//    List<Vuelo> vuelosDTOToVueloDTOs(List<VueloForm> vueloDTOs);
}
