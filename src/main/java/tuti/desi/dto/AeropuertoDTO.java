package tuti.desi.dto;
public record AeropuertoDTO(
        Long id,
        String icao,
        String name,
        String city,
        String country
) implements Comparable<AeropuertoDTO>{
    @Override
    public int compareTo(AeropuertoDTO aeropuertoDTO) {
        int compararPorPais = this.country.compareTo(aeropuertoDTO.country);

        if (compararPorPais != 0) {
            return compararPorPais;
        }

        return this.city.compareTo(aeropuertoDTO.city);
    }
}
