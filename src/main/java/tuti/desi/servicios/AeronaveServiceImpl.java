package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAeronaveRepo;
import tuti.desi.dto.AeronaveDTO;
import tuti.desi.mapper.AeronaveMapper;

import java.util.List;

@Service
public class AeronaveServiceImpl implements IAeronaveService{

    private final AeronaveMapper mapper;
    private final IAeronaveRepo repo;

    @Autowired
    public AeronaveServiceImpl(AeronaveMapper mapper, IAeronaveRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public List<AeronaveDTO> getAeronaves() {

        return mapper.aeronavesToAeronaveDTOs(repo.findAll());

    }
}
