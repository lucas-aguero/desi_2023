package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.dto.VueloDTO;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.enums.EstadoVuelo;
import tuti.desi.excepciones.VueloNoCreadoException;
import tuti.desi.excepciones.VueloNoEncontradoException;
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
        //Formatear fechas antes de crear vuelo
        try{
            Vuelo vuelo = vueloMapper.dtoToVuelo(vueloDTO);
            vuelo.setEstadoVuelo(EstadoVuelo.NORMAL);
            vuelo.setTipoVuelo();

            vueloRepo.save(vuelo);

            return vueloMapper.vueloToDTO(vuelo);

        }catch(Exception e){

            throw new VueloNoCreadoException("Ha ocurrido un error interno." +
                    " No se pudo crear el registro en la base de datos");
        }

    }

    @Override
    public VueloDTO findById(Long nroVuelo){


        Optional<Vuelo> vueloOptional = vueloRepo.findById(nroVuelo);

        if(vueloOptional.isPresent()){

            return vueloMapper.vueloToDTO(vueloOptional.get());
        }else{

            throw new VueloNoEncontradoException("El vuelo con nro: " + nroVuelo + "no existe.");
        }

    }
//    @Override
//    public List<VueloForm> getVuelos() {
//        return null;
//    }
}
