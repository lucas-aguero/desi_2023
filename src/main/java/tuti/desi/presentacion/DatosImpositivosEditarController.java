package tuti.desi.presentacion;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.servicios.DatosImpositivosService;

@Controller
@RequestMapping("/editarDatosImpositivos")
public class DatosImpositivosEditarController {

	@Autowired
    private DatosImpositivosService service;
	
	/*@RequestMapping(path = {"", "/{id}"},method=RequestMethod.GET)
    public String preparaForm(Model modelo, @PathVariable("id") Optional<Long> id) throws Exception {
    	if (id.isPresent()) {
    		DatosImpositivos entity = service.getById(id);
    		DatosImpositivosForm form = new DatosImpositivosForm(entity);
			modelo.addAttribute("formBean", form);
		} else {
 
	       modelo.addAttribute("formBean",new DatosImpositivosForm());
		}
       return "editarDatosImpositivos";
    }*/
	
	@GetMapping("/editarDatosImpositivos/{id}")
	public String editarDatosImpositivos(@PathVariable  Optional<Long> id, Model model) {
		if (id.isPresent()) {
    		DatosImpositivos entity = service.getById(id);
    		DatosImpositivosForm form = new DatosImpositivosForm(entity);
			model.addAttribute("formBean", form);
		} else {
	       model.addAttribute("formBean",new DatosImpositivosForm());
		}
       return "editarDatosImpositivos";
	}
	
	 @RequestMapping( method=RequestMethod.POST)
	    public String submit(@ModelAttribute("formBean") @Valid DatosImpositivosForm formBean,BindingResult result, ModelMap modelo,@RequestParam String action) throws Exception {
	    	
	    	
	    	if(action.equals("Aceptar"))
	    	{
	    	
	    		if(result.hasErrors())
	    		{	
	                
	    			modelo.addAttribute("formBean",formBean);
	    			 return "editarDatosImpositivos";
	    		}
	    		else
	    		{
	    			DatosImpositivos a = formBean.toPojo();
	    			service.saveOrUpdate(a);
	    			
	    			return "redirect:/datosImpositivos";
	    		}
			
	    	}
	    
	    	
	    	if(action.equals("Cancelar"))
	    	{
	    		modelo.clear();
	    		return "redirect:/";
	    	}
	    		
	    	return "redirect:/";
	    	
	    	
	    }
}
