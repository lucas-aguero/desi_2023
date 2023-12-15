package tuti.desi.presentacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tuti.desi.entidades.Cliente;
import tuti.desi.entidades.Pasaje;
import tuti.desi.entidades.Vuelo;
import tuti.desi.servicios.ClienteService;
import tuti.desi.servicios.PasajeService;
import tuti.desi.servicios.VueloService;

import java.util.List;
import java.util.UUID;

@Controller
public class PasajeController {

    private final ClienteService clienteService;
    private final PasajeService pasajeService;
    private final VueloService vueloService;

    public PasajeController(ClienteService clienteService, PasajeService pasajeService, VueloService vueloService) {
        this.clienteService = clienteService;
        this.pasajeService = pasajeService;
        this.vueloService = vueloService;
    }

    @GetMapping("/pasajeListar")
    public String listarPasajes(Model model) {
        List<Pasaje> pasajeLista = pasajeService.getAll();
        if (pasajeLista.isEmpty()) {
            model.addAttribute("error", "No hay Pasajes Vendidos.");
        } else {
        	model.addAttribute("listar", true);
            model.addAttribute("pasajeLista", pasajeLista);
        }        
        return "pasajeListar";
    }    
    
    @GetMapping("/pasajeNuevo")
    public String pasajeNuevo(@RequestParam("correoElectronico") String correoElectronico, Model model) {
    	if (correoElectronico != "") {
	        Cliente cliente = clienteService.buscarPorCorreoElectronico(correoElectronico);
	        if (cliente != null) {
	        	List<Vuelo> listaVuelos = vueloService.getVuelos();
	            model.addAttribute("clienteEncontrado", true);
	            model.addAttribute("cliente", cliente);
	            model.addAttribute("vuelos", listaVuelos);
	            return "pasajeNuevo";
	        } else {
	            model.addAttribute("error", "Cliente no encontrado");
	            return "pasajeNuevo";
	        }
    	} else {
            model.addAttribute("pasajeNuevo", true);    		
    		return "pasajeNuevo";
    	}
    }

    @GetMapping("/pasajeSeleccionar/resultado")
    public String seleccionarClienteVuelo(@RequestParam("correoElectronico") String correoElectronico, @RequestParam("nroVuelo") UUID nroVuelo, Model model) {
    	Cliente cliente = clienteService.buscarPorCorreoElectronico(correoElectronico);
    	Vuelo vuelo = vueloService.getByNroVuelo(nroVuelo);
    	List<Pasaje> listaPasajeVuelo = pasajeService.getPasajeNroVuelo(nroVuelo);
    	System.out.println(listaPasajeVuelo.toString());    	
        model.addAttribute("cliente", cliente);
        model.addAttribute("vuelo", vuelo);
        model.addAttribute("listaPasajeVuelo", listaPasajeVuelo);        
        model.addAttribute("clienteVuelo", true);        
        return "pasajeNuevo";
    }    

    @PostMapping("/pasajeGuardar")
    public String pasajeGuardar(@RequestParam("correoElectronico") String correoElectronico, @RequestParam("nroVuelo") UUID nroVuelo, @RequestParam("nroAsiento") int nroAsiento, Model model) {
    	Pasaje pasajeNuevo = new Pasaje();
    	
    	Cliente cliente = clienteService.buscarPorCorreoElectronico(correoElectronico);
    	Vuelo vuelo = vueloService.getByNroVuelo(nroVuelo);
    	
    	pasajeNuevo.setCliente(cliente);
    	pasajeNuevo.setVuelo(vuelo);
    	pasajeNuevo.setNroAsiento(nroAsiento);
    	try
    	{
    		pasajeService.guardarPasaje(pasajeNuevo);
    		model.addAttribute("mensaje", "Pasaje guardado con exito");        
    		
    	} catch (Exception e){
    		model.addAttribute("Error", "El Pasaje no pudo ser guardado.");
    	}
        return "pasajeNuevo";
    }    

}