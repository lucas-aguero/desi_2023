package tuti.desi.presentacion.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tuti.desi.entidades.Vuelo;

import java.util.List;

@Controller
public class VuelosController
{
    @GetMapping("/buscarVuelos")
    public String buscarVuelos(Model model)
    {
        model.addAttribute("vuelos", getVuelos());
        return "buscarVuelos";
    }

    @GetMapping("/buscarVuelos")
    public ModelAndView buscarVuelosForm()
    {
        return new ModelAndView("buscarVuelos", "vuelo", new Vuelo());
    }
    @PostMapping("/buscarVuelos")
    public String buscarVuelosPost(Model model, @RequestParam String origen, @RequestParam String destino, @RequestParam String fechaVuelo)
    {
        model.addAttribute("vuelos", getVuelos().stream().filter(vuelo -> vuelo.getOrigen().equalsIgnoreCase(origen) && vuelo.getDestino().equalsIgnoreCase(destino) && vuelo.getFechaVuelo().equalsIgnoreCase(fechaVuelo)).collect(Collectors.toList()));
        return "buscarVuelos";
    }
    @PostMapping("/buscarVuelos")
    public ModelAndView buscarVuelosSubmit(@ModelAttribute Vuelo vuelo)
    {
        // Aquí va la lógica para buscar vuelos en función de los parámetros ingresados
        return new ModelAndView("resultadosVuelos", "vuelos", vuelosEncontrados);
    }
    private List<Vuelo> getVuelos()
    {
        // Retornar la lista de vuelos disponibles
        return null;
    }
}