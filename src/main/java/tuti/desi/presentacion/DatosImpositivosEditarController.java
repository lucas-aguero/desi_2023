package tuti.desi.presentacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.servicios.DatosImpositivosService;

@Controller
@RequestMapping("/editarDatosImpositivos")
public class DatosImpositivosEditarController {

    @Autowired
    private DatosImpositivosService datosImpositivosService;

    @GetMapping
    public String prepararForm(Model model) {
        DatosImpositivosForm form = new DatosImpositivosForm();
        model.addAttribute("formBean", form);
        return "editarDatosImpositivos";
    }

    @PostMapping
    public String submit(
            @ModelAttribute("formBean") @Valid DatosImpositivosForm formBean,
            BindingResult result,
            ModelMap modelo,
            @RequestParam String action) throws Exception {

        if (action.equals("Guardar")) {
            if (result.hasErrors()) {
                modelo.addAttribute("formBean", formBean);
                return "editarDatosImpositivos";
            } else {
                DatosImpositivos datosImpositivos = formBean.toPojo();
                datosImpositivosService.save(datosImpositivos);
                return "redirect:/datosImpositivos";
            }
        }

        if (action.equals("Cancelar")) {
            modelo.clear();
            return "redirect:/";
        }

        return "redirect:/";
    }
}
