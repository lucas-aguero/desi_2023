package tuti.desi.util.faker;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuti.desi.accesoDatos.IAerolineaRepo;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.accesoDatos.IAeropuertoRepo;
import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.entidades.Aerolinea;
import tuti.desi.entidades.Aeronave;
import tuti.desi.entidades.Aeropuerto;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.enums.EstadoVuelo;
import tuti.desi.entidades.enums.TipoVuelo;
import tuti.desi.excepciones.aerolineaexception.AerolineaNoCreadaException;
import tuti.desi.excepciones.aeronaveexception.AeronaveNoCreadaException;
import tuti.desi.excepciones.aeropuertoexception.AeropuertoNoEncontradoException;
import tuti.desi.excepciones.vueloexception.VueloConDestinoYFechaExistenteException;
import tuti.desi.excepciones.vueloexception.VueloNoCreadoException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class VueloCreator {

    private final Faker faker;
    private final AerolineaCreator aerolineaCreator;
    private final AeronaveCreator aeronaveCreator;
    private final IVueloRepo vueloRepo;
    private final IAeronaveRepo aeronaveRepo;
    private final IAerolineaRepo aerolineaRepo;
    private final IAeropuertoRepo aeropuertoRepo;

    @Autowired
    public VueloCreator(Faker faker, AerolineaCreator aerolineaCreator, AeronaveCreator aeronaveCreator, IVueloRepo vueloRepo, IAeronaveRepo aeronaveRepo, IAerolineaRepo aerolineaRepo, IAeropuertoRepo aeropuertoRepo) {
        this.faker = faker;
        this.aerolineaCreator = aerolineaCreator;
        this.aeronaveCreator = aeronaveCreator;
        this.vueloRepo = vueloRepo;
        this.aeronaveRepo = aeronaveRepo;
        this.aerolineaRepo = aerolineaRepo;
        this.aeropuertoRepo = aeropuertoRepo;
    }

    public Vuelo createVuelo(boolean saliente, boolean nacional){
        Vuelo vuelo = new Vuelo();
        Aerolinea aerolinea = new Aerolinea();
        Aeronave aeronave = new Aeronave();
        Aeropuerto origen = new Aeropuerto();
        Aeropuerto destino = new Aeropuerto();
        LocalDate fechaPartida =  crearFechaAleatoria();
        LocalTime horaPartida =  crearHoraAleatoria();

        //Chequear si existen aeropuertos cargados
        Optional<Aeropuerto> origenOptional = null;
        Optional<Aeropuerto> destinoOptional= null;

        try{
            if(saliente){

                origenOptional = aeropuertoRepo.findAeropuertoLocal();

                if(nacional){

                    destinoOptional = aeropuertoRepo.findRandomAeropuertoArgentino();

                }else{

                    destinoOptional = aeropuertoRepo.findRandomAeropuertoExtranjero();

                }

                if(origenOptional.isEmpty()){
                    throw new AeropuertoNoEncontradoException("No existe aeropuerto local registrado. " +
                            "Imposible crear un nuevo vuelo. " +
                            "Registre un aeropuerto local e intente nuevamente");
                }

                if(destinoOptional.isEmpty()){
                    throw new AeropuertoNoEncontradoException("No existen aeropuertos registrados. " +
                            "Imposible crear un nuevo vuelo. " +
                            "Registre al menos un aeropuerto e intente nuevamente");
                }

                //entrante
            }else{

                if(nacional){

                    origenOptional = aeropuertoRepo.findRandomAeropuertoArgentino();

                }else{

                    origenOptional = aeropuertoRepo.findRandomAeropuertoExtranjero();

                }

                destinoOptional = aeropuertoRepo.findAeropuertoLocal();

                if(origenOptional.isEmpty()){
                    throw new AeropuertoNoEncontradoException("No existen aeropuertos registrados. " +
                            "Imposible crear un nuevo vuelo. " +
                            "Registre al menos un aeropuerto e intente nuevamente");
                }

                if(destinoOptional.isEmpty()){
                    throw new AeropuertoNoEncontradoException("No existe aeropuerto local registrado. " +
                            "Imposible crear un nuevo vuelo. " +
                            "Registre un aeropuerto local e intente nuevamente");
                }

            }

        }catch(Exception e){
            throw new AeropuertoNoEncontradoException("No existen aeropuertos registrados. " +
                    "Imposible crear un nuevo vuelo. " +
                    "Registre al menos un aeropuerto e intente nuevamente");
        }


        origen = origenOptional.get();
        destino = destinoOptional.get();

        //Chequear si existen aeronaves cargadas
        if(aeronaveRepo.findRandomAeronave().isEmpty()) {
            aeronaveCreator.persistAeronaves(16);
        }

        aeronave = aeronaveRepo.findRandomAeronave().get();

        try {
            aeronaveRepo.save(aeronave);

        } catch (Exception e){
            throw new AeronaveNoCreadaException();
        }

        //Chequear si existen aerolineas cargadas
        if(aerolineaRepo.findRandomAerolinea().isEmpty()) {
            aerolineaCreator.persistAerolineas(32);
        }

        aerolinea = aerolineaRepo.findRandomAerolinea().get();

        try{
            aerolineaRepo.save(aerolinea);
        }catch(Exception e){
            throw new AerolineaNoCreadaException();
        }

        try{
            vuelo = Vuelo.builder()
                    .fechaPartida(crearFechaAleatoria())
                    .horaPartida(crearHoraAleatoria())
                    .aerolinea(aerolinea)
                    .aeronave(aeronave)
                    .nroAsientos(aeronave.getCapacidad())
                    .origen(origen)
                    .destino(destino)
                    .precioNeto(crearPrecioNetoAleatorio())
                    .build();

            vuelo.setEstadoVuelo(EstadoVuelo.NORMAL);

        }catch(Exception e){

            throw new VueloNoCreadoException();

        }

        return vuelo;

    }

    public String persistirVuelos(boolean saliente, boolean nacional){

        for (int i = 0; i < 4; i++) {

            try {

                Vuelo vuelo = createVuelo(saliente, nacional);

                if(aeropuertoRepo.findByIcao(vuelo.getOrigen().getIcao())
                        .equals(aeropuertoRepo.findByIcao(vuelo.getDestino().getIcao())))
                {
                    System.out.println("origen-destino iguales");
                    --i;
                    break;
                }

                if(vueloRepo.existsByDestinoIdAndFechaPartida(vuelo.getDestino().getId(),
                        vuelo.getFechaPartida())
                        && !vuelo.getDestino().getIcao().equals("SAAV")){

                    System.out.println("destino-fecha ya existe");
                    --i;
                    break;
                }
                if(vueloRepo.existsByOrigenIdAndFechaPartida(vuelo.getOrigen().getId(),
                        vuelo.getFechaPartida())
                        && !vuelo.getOrigen().getIcao().equals("SAAV")){

                    System.out.println("origen-fecha ya existe");
                    --i;
                    break;
                }

                if(nacional){
                    vuelo.setTipoVuelo(TipoVuelo.NACIONAL);
                }else{
                    vuelo.setTipoVuelo(TipoVuelo.INTERNACIONAL);
                }

                vueloRepo.save(vuelo);

            }catch(Exception e){
                throw new VueloNoCreadoException();
            }
        }
        return "Inicialización de registros de aeronaves exitosa. " +
                "\nNro de registros creados: " + vueloRepo.count();
    }

    public String persistirVuelos(boolean saliente, boolean nacional, int cant){

        for (int i = 0; i < cant; i++) {

            try {

                Vuelo vuelo = createVuelo(saliente, nacional);

                if(aeropuertoRepo.findByIcao(vuelo.getOrigen().getIcao())
                        .equals(aeropuertoRepo.findByIcao(vuelo.getDestino().getIcao())))
                {
                    System.out.println("origen-destino iguales");
                    --i;
                    break;
                }

                if(vueloRepo.existsByDestinoIdAndFechaPartida(vuelo.getDestino().getId(),
                        vuelo.getFechaPartida())
                        && !vuelo.getDestino().getIcao().equals("SAAV")){

                    System.out.println("destino-fecha ya existe");
                    --i;
                    break;
                }
                if(vueloRepo.existsByOrigenIdAndFechaPartida(vuelo.getOrigen().getId(),
                        vuelo.getFechaPartida())
                        && !vuelo.getOrigen().getIcao().equals("SAAV")){

                    System.out.println("origen-fecha ya existe");
                    --i;
                    break;
                }

                if(nacional){
                    vuelo.setTipoVuelo(TipoVuelo.NACIONAL);
                }else{
                    vuelo.setTipoVuelo(TipoVuelo.INTERNACIONAL);
                }

                vueloRepo.save(vuelo);

            }catch(Exception e){
                throw new VueloNoCreadoException();
            }
        }
        return "Inicialización de registros de aeronaves exitosa. " +
                "\nNro de registros creados: " + vueloRepo.count();
    }

    public String persistirLotesVuelos(int cant){

        //SALIENTE - NACIONAL
        persistirVuelos(true, true, cant);
        //ENTRANTE - NACIONAL
        persistirVuelos(false, true, cant);
        //SALIENTE - INTERNACIONAL
        persistirVuelos(true, false, cant);
        //ENTRANTE - INTERNACIONAL
        persistirVuelos(false, false, cant);

        return "Inicialización de registros de aeronaves exitosa. " +
                "\nNro de registros creados: " + vueloRepo.count();
    }

    private BigDecimal crearPrecioNetoAleatorio(){
        double randomDouble = faker.number().randomDouble(2,20000,500000);

        return BigDecimal.valueOf(randomDouble);
    }

    private LocalDate crearFechaAleatoria(){

        return (faker.date()
                .future(180,0, TimeUnit.DAYS))
                .toLocalDateTime()
                .toLocalDate();
    }

    private LocalTime crearHoraAleatoria(){

        return (faker.date().future(24, 0, TimeUnit.HOURS))
                .toLocalDateTime()
                .toLocalTime();

    }

}
