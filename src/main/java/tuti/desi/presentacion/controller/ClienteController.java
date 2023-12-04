package tuti.desi.presentacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import tuti.desi.entidades.Cliente;
import tuti.desi.servicios.ClienteService;
import tuti.desi.presentacion.form.ClienteForm;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService datosClienteService;

    @GetMapping("/listarClientes")
    public String listarClientes(Model model) {
        List<Cliente> datosClienteList = datosClienteService.getAll();
        model.addAttribute("datosClienteList", datosClienteList);
        return "listarClientes";
    }

    @GetMapping("/editarCliente/{id}")
    public String editar(@PathVariable Long id, Model model) {
    	Cliente datosCliente = datosClienteService.getById(id);
        model.addAttribute("datosCliente", datosCliente);
        return "editarCliente";
    }

    @PostMapping("/guardarCliente")
    public String guardarDatos(@ModelAttribute("datosCliente") @Valid Cliente datosCliente) throws Exception {
    	datosClienteService.save(datosCliente);
        return "redirect:/cliente/listarClientes";
    }
}