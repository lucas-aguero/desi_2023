package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.dto.VueloDTO;
import tuti.desi.entidades.Vuelo;

import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    IVueloRepo vueloRepo;

    @Override
    public VueloDTO crearVuelo(VueloDTO vueloDTO){

        return null;
    }

    @Override
    public VueloDTO findById(Long nroVuelo){


        Optional<Vuelo> vueloOptional = vueloRepo.findById(nroVuelo);

        if(vueloOptional.isPresent()){
            var vuelo = Vuelo.builder()
                    .nroVuelo(vueloOptional.get().getNroVuelo())
                    .tipoVuelo(vueloOptional.get().getTipoVuelo())
                    .avion(vueloOptional.get().getAvion())
                    .origen(vueloOptional.get().getOrigen())
                    .destino(vueloOptional.get().getDestino())
                    .horaPartida(vueloOptional.get().getHoraPartida())
                    .nroAsientos(vueloOptional.get().getNroAsientos())
                    .precio(vueloOptional.get().getPrecio())
                    .build();

            return null;
        }

        return null;

    }
}
