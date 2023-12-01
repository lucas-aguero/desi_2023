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
        return this.city().compareTo(aeropuertoDTO.city);
    }
}
