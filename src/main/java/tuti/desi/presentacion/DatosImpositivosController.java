package tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.servicios.DatosImpositivosService;

import java.util.List;

@Controller
@RequestMapping("/datosImpositivos")
public class DatosImpositivosController {

    @Autowired
    private DatosImpositivosService datosImpositivosService;

    @GetMapping("/listarDatosImpositivos")
    public String listar(Model model) {
        List<DatosImpositivos> datosImpositivosList = datosImpositivosService.getAll();
        model.addAttribute("datosImpositivosList", datosImpositivosList);
        return "listarDatosImpositivos";
    }

    @GetMapping("/editarDatosImpositivos/{id}")
    public String editar(@PathVariable Long id, Model model) {
        DatosImpositivos datosImpositivos = datosImpositivosService.getById(id);
        model.addAttribute("datosImpositivos", datosImpositivos);
        return "editarDatosImpositivos";
    }

    @PostMapping("/guardarDatos")
    public String guardarDatos(@ModelAttribute("datosImpositivos") @Valid DatosImpositivos datosImpositivos) throws Exception {
        datosImpositivosService.save(datosImpositivos);
        return "redirect:/datosImpositivos/listarDatosImpositivos";
    }
}
