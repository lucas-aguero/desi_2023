package tuti.desi.presentacion.form;

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

	private int iva;
	private Double cotizacionDolar;
	private Double tasaAeroportuariaNacional;
	private Double tasaAeroportuariaInternacional;
	
	
	public DatosImpositivosForm(DatosImpositivos a) {
		super();
		this.cotizacionDolar = a.getCotizacionDolar();
		this.iva = a.getIva();
		this.tasaAeroportuariaInternacional = a.getTasaAeroportuariaInternacional();
		this.tasaAeroportuariaNacional = a.getTasaAeroportuariaNacional();
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
		a.setCotizacionDolar(this.cotizacionDolar);
		a.setTasaAeroportuariaInternacional(this.tasaAeroportuariaInternacional);
		a.setTasaAeroportuariaNacional(this.tasaAeroportuariaNacional);
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
		return fecha;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fecha = fechaModificacion;
	}
}
