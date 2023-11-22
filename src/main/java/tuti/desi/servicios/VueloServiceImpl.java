package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.dto.VueloDTO;
import tuti.desi.entidades.Vuelo;
import tuti.desi.excepciones.VueloNoCreadoException;
import tuti.desi.mapper.VueloMapper;

import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    private final IVueloRepo vueloRepo;
    private final VueloMapper vueloMapper;

    @Autowired
    public VueloServiceImpl(IVueloRepo vueloRepo, VueloMapper vueloMapper) {
        this.vueloRepo = vueloRepo;
        this.vueloMapper = vueloMapper;
    }


    @Override
    public VueloDTO crearVuelo(VueloDTO vueloDTO) throws VueloNoCreadoException {
        try{
            Vuelo vuelo = vueloMapper.vueloDTOToVuelo(vueloDTO);
            vueloRepo.save(vuelo);

            return vueloDTO;

        }catch(Exception e){

            throw new VueloNoCreadoException("Ha ocurrido un error interno." +
                    " No se pudo crear el registro en la base de datos");
        }

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
