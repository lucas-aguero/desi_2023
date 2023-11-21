package tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

    @GetMapping
    public String listarYPrepararForm(Model model) {
        List<DatosImpositivos> datosImpositivosList = datosImpositivosService.getAll();

        if (!datosImpositivosList.isEmpty()) {
            // Si hay datos, agregarlos al modelo
            model.addAttribute("datosImpositivosList", datosImpositivosList);
        } else {
            // Si no hay datos, agregar un formulario vacío al modelo
            DatosImpositivosForm form = new DatosImpositivosForm();
            model.addAttribute("formBean", form);
        }

        return "datosImpositivos";
    }
     
      @RequestMapping( method=RequestMethod.POST)
    public String submit( @ModelAttribute("formBean") @Valid DatosImpositivosForm formBean,BindingResult result, ModelMap modelo,@RequestParam String action)  {
    	
    	if(action.equals("Cancelar"))
    	{
    		modelo.clear();
    		return "redirect:/";
    	}
    		    	
    	
    	if(action.equals("Editar"))
    	{
    		modelo.clear();
    		return "redirect:/editarDatosImpositivos";
    	}
    		
    	return "redirect:/";
    	
    	
    }
}
