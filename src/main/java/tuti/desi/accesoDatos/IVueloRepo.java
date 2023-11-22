package tuti.desi.accesoDatos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Vuelo;

@Repository
public interface IVueloRepo extends JpaRepository<Vuelo, Long> {

}
