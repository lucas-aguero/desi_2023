package tuti.desi.dto;

public record AeronaveDTO(

        Long id,
        String modelo,
        int nroFilas,
        int asientosPorFila,
        int capacidad

) implements Comparable<AeronaveDTO>{
    @Override
    public int compareTo(AeronaveDTO aeronaveDTO) {
        return this.modelo.compareTo(aeronaveDTO.modelo);
    }
}
