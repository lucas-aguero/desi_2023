package tuti.desi.presentacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuti.desi.entidades.Cliente;
import tuti.desi.servicios.ClienteService;
import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService datosClienteService;
    
    @GetMapping("/clientesListar")
    public String listarClientes(Model model) {
        List<Cliente> datosClienteList = datosClienteService.getAll();
        if (datosClienteList.isEmpty()) {
            model.addAttribute("error", "No hay Clientes.");
        } else {
            model.addAttribute("datosClienteList", datosClienteList);
        }        
        return "clientesListar";
    }

    @GetMapping("/clientesBuscar")
    public String mostrarFormularioBuscarCliente(Model model) {
        model.addAttribute("correoElectronico", "");
        return "clientesBuscar";
    }

    @GetMapping("/clientesBuscar/resultado")
    public String buscarClientePorEmail(@RequestParam("correoElectronico") String correoElectronico, Model model) {
        Cliente cliente = datosClienteService.buscarPorCorreoElectronico(correoElectronico);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "clientesDetalle";
        } else {
            model.addAttribute("mensaje", "Cliente no encontrado");
            return "clientesDetalle";
        }
    }    
    
 
}