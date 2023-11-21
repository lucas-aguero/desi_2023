package tuti.desi.presentacion;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import tuti.desi.servicios.VueloServiceImpl;

@Validated
@RestController
public class VueloController {

    private final VueloServiceImpl vueloServiceImpl;


    @Autowired
    public VueloController(VueloServiceImpl vueloServiceImpl) {
        VueloServiceImpl = vueloServiceImpl;
    }


}
