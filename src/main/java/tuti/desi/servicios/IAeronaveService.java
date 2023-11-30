package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.dto.AeronaveDTO;

import java.util.List;

@Service
public interface IAeronaveService {
    public List<AeronaveDTO> getAeronaves();
}
