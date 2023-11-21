package tuti.desi.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class DatosImpositivos {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@DecimalMin("0")
	@DecimalMax("100")
	private int iva;
	
	@NotNull
	@DecimalMin("0.00")
	private Double tasaAeroportuariaNacional;
	
	@NotNull
	@DecimalMin("0.00")
	private Double tasaAeroportuariaInternacional;
	
	@NotNull
	@DecimalMin("0.00")
	private Double cotizacionDolar;
	
	 @NotNull
	 @Column(name = "fecha_modificacion")
	 private LocalDateTime fechaModificacion;
	
	// Getter y setters

	public @NotNull(message = "Debe ingresar el iva") @Min(value = 0, message = "El porcentaje de iva debe ser mayor o igual a 0") @Max(value = 100, message = "El porcentaje de iva debe ser menor o igual a 100") int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public Double getTasaAeroportuariaNacional() {
		return tasaAeroportuariaNacional;
	}

	public void setTasaAeroportuariaNacional(Double tasaAeroportuariaNacional) {
		this.tasaAeroportuariaNacional = tasaAeroportuariaNacional;
	}

	public Double getTasaAeroportuariaInternacional() {
		return tasaAeroportuariaInternacional;
	}

	public void setTasaAeroportuariaInternacional(Double tasaAeroportuariaInternacional) {
		this.tasaAeroportuariaInternacional = tasaAeroportuariaInternacional;
	}

	public Double getCotizacionDolar() {
		return cotizacionDolar;
	}

	public void setCotizacionDolar(Double cotizacionDolar) {
		this.cotizacionDolar = cotizacionDolar;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
}