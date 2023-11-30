package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAerolineaRepo;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.mapper.AerolineaMapper;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService{

    private final AerolineaMapper mapper;
    private final IAerolineaRepo repo;

    @Autowired
    public AerolineaServiceImpl(AerolineaMapper mapper, IAerolineaRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public List<AerolineaDTO> getAerolineas() {

        return mapper.aerolineasToDTOs(repo.findAll());

    }
//    @Override
//    public Optional<AerolineaDTO> findByNombre() {
//        return Optional.empty();
//    }
}
