package tuti.desi.presentacion;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.excepciones.Excepcion;
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
            @RequestParam String action) {
    	
    	  if (action.equals("Guardar")) {
    	        validarDatosImpositivos(formBean.toPojo(), result);

    	        if (result.hasErrors()) {
    	            modelo.addAttribute("formBean", formBean);
    	            return "editarDatosImpositivos";
    	        } else {
                    try {
                        datosImpositivosService.save(formBean.toPojo());
                        return "redirect:/datosImpositivos";
                    } catch (Excepcion e) {
                        result.rejectValue("iva", "iva.invalid", e.getMessage());
                        modelo.addAttribute("formBean", formBean);
                        return "editarDatosImpositivos";
                    }
                }
    	    }


        if (action.equals("Cancelar")) {
            modelo.clear();
            return "redirect:/datosImpositivos"; 
        }

        return "redirect:/";
    }
    
    private void validarDatosImpositivos(DatosImpositivos datosImpositivos,  BindingResult result){
		Integer iva = datosImpositivos.getIva();
        if ( iva == null || datosImpositivos.getIva() < 0 || datosImpositivos.getIva() > 100) {
            result.rejectValue("iva", "iva.invalid","El campo IVA debe estar entre 0 y 100. Recuerda que es un porcentaje.");
        }
        
        if (datosImpositivos.getTasaAeroportuariaNacional() == null || datosImpositivos.getTasaAeroportuariaNacional() < 0 || !validarDecimales( datosImpositivos.getTasaAeroportuariaNacional(), 2)) {
        	result.rejectValue("tasaAeroportuariaNacional", "tasaAeroportuariaNacional.invalid","El campo de Tasa Aeroportuaria Nacional no puede ser negativo y cómo máximo debe tener dos decimales. Debe estar en pesos.");
        }
        
        if (datosImpositivos.getTasaAeroportuariaInternacional() == null || datosImpositivos.getTasaAeroportuariaInternacional() < 0 || !validarDecimales(datosImpositivos.getTasaAeroportuariaInternacional(), 2)) {
        	result.rejectValue("tasaAeroportuariaInternacional", "tasaAeroportuariaInternacional.invalid", "El campo de Tasa Aeroportuaria Internacional no puede ser negativo y cómo máximo debe tener dos decimales. Debe estar en dólares.");
        }
        
        if (datosImpositivos.getCotizacionDolar() == null || datosImpositivos.getCotizacionDolar() < 0 || !validarDecimales( datosImpositivos.getCotizacionDolar(), 2)) {
        	 result.rejectValue("cotizacionDolar", "cotizacionDolar.invalid","El campo de Cotización del dolar no puede ser negativo y cómo máximo debe tener dos decimales. Debe estar en pesos.");
        }
    }
	
    private boolean validarDecimales(Double valor, int maxDecimales){
        if (valor != null) {
            String valorComoString = String.valueOf(valor);
            Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1," + maxDecimales + "})?$");
            java.util.regex.Matcher matcher = pattern.matcher(valorComoString);

            if (!matcher.matches()) {
                return false;
            }
            return true; 
        }
        return false;
    }
}
