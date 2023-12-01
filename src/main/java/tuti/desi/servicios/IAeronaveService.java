package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.dto.AeronaveDTO;

import java.util.TreeSet;

@Service
public interface IAeronaveService {
    public TreeSet<AeronaveDTO> getAeronaves();
}
