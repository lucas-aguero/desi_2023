package tuti.desi.dto;

public record AerolineaDTO (
    Long id,
    String nombre
) implements Comparable<AerolineaDTO> {
    @Override
    public int compareTo(AerolineaDTO aerolineaDTO) {
        return this.nombre.compareTo(String.valueOf(aerolineaDTO.nombre));
    }
}
