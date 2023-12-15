package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.entidades.enums.TipoVuelo;
import tuti.desi.excepciones.vueloexception.VueloConOrigenYFechaExistenteException;
import tuti.desi.excepciones.vueloexception.VueloConDestinoYFechaExistenteException;
import tuti.desi.excepciones.vueloexception.VueloNoCreadoException;
import tuti.desi.excepciones.vueloexception.VueloConOrigenYDestinoDuplicados;
import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.enums.EstadoVuelo;
import tuti.desi.mapper.VueloMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void crearVuelo(NuevoVueloForm vueloForm){

        Vuelo vuelo = vueloMapper.formToVuelo(vueloForm);

        if(vueloRepo.existsByDestinoIdAndFechaPartida(vueloForm.getDestinoId(),
                vueloForm.getFechaPartida())
                && !aeropuertoRepo.findById(vueloForm.getDestinoId()).get().getIcao().equals("SAAV")){

            throw new VueloConDestinoYFechaExistenteException("Ya existe un vuelo con ese destino para esa fecha");

        } else if (vueloRepo.existsByOrigenIdAndFechaPartida(vueloForm.getOrigenId(),
                    vueloForm.getFechaPartida())
                    && !aeropuertoRepo.findById(vueloForm.getOrigenId()).get().getIcao().equals("SAAV")){

            throw new VueloConOrigenYFechaExistenteException("Ya existe un vuelo con ese origen para esa fecha");

        }

        if(aeropuertoRepo.findById(vueloForm.getOrigenId())
                .equals(aeropuertoRepo.findById(vueloForm.getDestinoId()))){

            throw new VueloConOrigenYDestinoDuplicados("El vuelo no puede tener mismo origen y destino");

        }
        else {

            try{


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

           }catch(Exception e){

            throw new VueloNoCreadoException("Ha ocurrido un error interno." +
                    " No se pudo crear el registro en la base de datos");
            }

        }

    }
    
    @Override
    public List<Vuelo> getVuelos() {
        return vueloRepo.findAll();
    }

    @Override
    public long contarAeropuertos() {
        return vueloRepo.count();
    }

    @Override
    public Vuelo getByNroVuelo(UUID nroVuelo) {
    	return vueloRepo.findByNroVuelo(nroVuelo);
    }


    /*public Vuelo v(){
        Optional<Vuelo> vueloOptional = vueloRepo.findByFechaPartida(LocalDate.of(2023,22,22);

        if(vueloOptional.isPresent()){

            Vuelo vuelo = vueloOptional.get();

            return vuelo;
        }

        return null;
    }*/
}
