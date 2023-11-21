package tuti.desi.servicios;

import lombok.val;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.entidades.Vuelo;
import tuti.desi.presentacion.form.CrearVueloForm;

import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    IVueloRepo vueloRepo;

    public void crearVuelo(CrearVueloForm vueloForm){

    }

    public Vuelo findById(Long nroVuelo){


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

            return vuelo;
        }

        return null;

    }

}
