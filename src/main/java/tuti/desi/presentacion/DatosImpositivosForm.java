package tuti.desi.presentacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import tuti.desi.entidades.DatosImpositivos;

public class DatosImpositivosForm {
	
	private LocalDateTime fecha;
	private Long id;

	@NotNull (message = "Debe ingresar el iva")
	@Min(value=0, message = "El porcentaje de iva debe ser mayor o igual a 0")
	@Max(value=100, message = "El porcentaje de iva debe ser menor o igual a 100")
	private int iva;
	
	@NotNull (message = "Debe ingresar la cotización del dolar")
	@DecimalMin(value="0.00", message = "El valor de la cotización debe ser mayor o igual a 0 y no debe exceder los dos decimales.")
	private Double cotizacion_dolar;
	
	@NotNull (message = "Debe ingresar el valor de la tasa aeroportuaria Nacional")
	@DecimalMin(value="0.00", message = "El valor de la tasa debe ser mayor o igual a 0 y no debe exceder los dos decimales.")
	private Double tasa_aeroportuaria_nacional;
	
	@NotNull (message = "Debe ingresar el valor de la tasa aeroportuaria Internacional")
	@DecimalMin(value="0.00", message = "El valor de la tasa debe ser mayor o igual a 0 y no debe exceder los dos decimales.")
	private Double tasa_aeroportuaria_internacional;
	
	
	public DatosImpositivosForm(DatosImpositivos a) {
		super();
		this.cotizacion_dolar = a.getCotizacionDolar();
		this.iva = a.getIva();
		this.tasa_aeroportuaria_internacional = a.getTasaAeroportuariaInternacional();
		this.tasa_aeroportuaria_nacional = a.getTasaAeroportuariaNacional();
		this.fecha = LocalDateTime.now();
		this.id = a.getId();
	}
	
	public DatosImpositivosForm() {
		super();
	}
	
	public DatosImpositivos toPojo()
	{
		DatosImpositivos a = new DatosImpositivos();
		a.setIva(this.iva);
		a.setCotizacionDolar(this.cotizacion_dolar);
		a.setTasaAeroportuariaInternacional(this.tasa_aeroportuaria_internacional);
		a.setTasaAeroportuariaNacional(this.tasa_aeroportuaria_nacional);
		a.setFechaModificacion(LocalDateTime.now());
		a.setId(this.id);
		return a;
	}
	
	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}	
	
	public int getIva() {
		return iva;
	}
	
	public void setIva(int iva) {
		this.iva = iva;
	}

	public Double getTasaAeroportuariaNacional() {
		return tasa_aeroportuaria_nacional;
	}

	public void setTasaAeroportuariaNacional(Double tasaAeroportuariaNacional) {
		this.tasa_aeroportuaria_nacional = tasaAeroportuariaNacional;
	}

	public Double getTasaAeroportuariaInternacional() {
		return tasa_aeroportuaria_internacional;
	}

	public void setTasaAeroportuariaInternacional(Double tasaAeroportuariaInternacional) {
		this.tasa_aeroportuaria_internacional = tasaAeroportuariaInternacional;
	}

	public Double getCotizacionDolar() {
		return cotizacion_dolar;
	}

	public void setCotizacionDolar(Double cotizacionDolar) {
		this.cotizacion_dolar = cotizacionDolar;
	}

	public LocalDateTime getFechaModificacion() {
		return fecha;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fecha = fechaModificacion;
	}
}
