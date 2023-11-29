package tuti.desi.dto;

import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;

public record AeropuertoDTO(

        Long id,
        String icao,
        String name,
        String city,
        String country
) {
}
