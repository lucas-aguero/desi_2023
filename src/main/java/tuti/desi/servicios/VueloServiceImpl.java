package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.entidades.enums.TipoVuelo;
import tuti.desi.excepciones.vueloexception.VueloConDestinoYFechaExistenteException;
import tuti.desi.excepciones.vueloexception.VueloNoCreadoException;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.enums.EstadoVuelo;
import tuti.desi.excepciones.vueloexception.VueloNoEncontradoException;
import tuti.desi.mapper.VueloMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    private final IVueloRepo vueloRepo;
    private final IAeropuertoRepo aeropuertoRepo;
    private final IAeronaveRepo aeronaveRepo;
    private final VueloMapper vueloMapper;


    @Autowired
    public VueloServiceImpl(IVueloRepo vueloRepo, IAeropuertoRepo aeropuertoRepo,
                            IAeronaveRepo aeronaveRepo, VueloMapper vueloMapper) {
        this.vueloRepo = vueloRepo;
        this.aeropuertoRepo = aeropuertoRepo;
        this.aeronaveRepo = aeronaveRepo;
        this.vueloMapper = vueloMapper;
    }


    @Override
    public NuevoVueloForm crearVuelo(NuevoVueloForm vueloForm){

        if(vueloRepo.existsByDestinoIdAndFechaPartida(vueloForm.getDestinoId(),
                LocalDate.parse(vueloForm.getFechaPartida()))){

            throw new VueloConDestinoYFechaExistenteException("Ya existe un vuelo con ese destino para esa fecha");

        } else {

            try{

            Vuelo vuelo = vueloMapper.formToVuelo(vueloForm);
            vuelo.setEstadoVuelo(EstadoVuelo.NORMAL);
            vuelo.setNroAsientos(aeronaveRepo.getNroAsientos(vuelo.getAeronave().getId()));

            if(!aeropuertoRepo.existsByIdAndCountryEqualsAR(vuelo.getDestino().getId())
            || !aeropuertoRepo.existsByIdAndCountryEqualsAR(vuelo.getOrigen().getId())){

                vuelo.setTipoVuelo(TipoVuelo.INTERNACIONAL);

            } else {
                vuelo.setTipoVuelo(TipoVuelo.NACIONAL);
            }

            vueloRepo.save(vuelo);

            System.out.println(vuelo.toString());

            return vueloMapper.vueloToForm(vuelo);

           }catch(Exception e){

            throw new VueloNoCreadoException("Ha ocurrido un error interno." +
                    " No se pudo crear el registro en la base de datos");
            }

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
