package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.enums.EstadoVuelo;
import tuti.desi.excepciones.VueloNoCreadoException;
import tuti.desi.excepciones.VueloNoEncontradoException;
import tuti.desi.mapper.VueloMapper;

import java.util.List;
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
    public NuevoVueloForm crearVuelo(NuevoVueloForm vueloForm) throws VueloNoCreadoException {
        //Formatear fechas antes de crear vuelo
        try{
            Vuelo vuelo = vueloMapper.formToVuelo(vueloForm);
            vuelo.setEstadoVuelo(EstadoVuelo.NORMAL);
            vuelo.setTipoVuelo();

            vueloRepo.save(vuelo);

            return vueloMapper.vueloToForm(vuelo);

        }catch(Exception e){

            throw new VueloNoCreadoException("Ha ocurrido un error interno." +
                    " No se pudo crear el registro en la base de datos");
        }

    }

    @Override
    public NuevoVueloForm findById(Long nroVuelo){


        Optional<Vuelo> vueloOptional = vueloRepo.findById(nroVuelo);

        if(vueloOptional.isPresent()){

            return vueloMapper.vueloToForm(vueloOptional.get());
        }else{

            throw new VueloNoEncontradoException("El vuelo con nro: " + nroVuelo + "no existe.");
        }

    }

    @Override
    public List<Vuelo> getVuelos() {

        return vueloRepo.findAll();

    }

}
