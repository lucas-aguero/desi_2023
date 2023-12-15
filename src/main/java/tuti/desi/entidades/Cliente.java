package tuti.desi.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String domicilio;
    
    @NotNull
    @Column(name = "correo_electronico") 
    private String correoElectronico;
    
    @NotNull
    @Column(name = "fecha_nacimiento") 
	private Date fechaNacimiento;
    
    @NotNull
    private String pasaporte;
    
    // Getters
    public Long getId() {
    	return id;
    }    
    public String getNombre() {
    	return nombre;
    }

    public String getApellido() {
    	return apellido;
    }
    
    public String getDomicilio() {
    	return domicilio;
    }
    
    public String getCorreoElectronico() {
    	return correoElectronico;
    }
    
    public Date getFechaNacimiento() {
    	return fechaNacimiento;
    }
    
    public String getPasaporte() {
    	return pasaporte;
    }
    
    // Setters
    public void setId(Long datoId) {
    	this.id = datoId;
    }    
    public void setNombre(String datoNombre) {
    	this.nombre = datoNombre;
    }

    public void setApellido(String datoApellido) {
    	this.apellido = datoApellido;
    }
    
    public void setDomicilio(String datoDomicilio) {
    	this.domicilio = datoDomicilio;
    }
    
    public void setCorreoElectronico(String datoCorreoElectronico) {
    	this.correoElectronico = datoCorreoElectronico;
    }
    
    public void setFechaNacimiento(Date datoFechaNacimiento) {
    	this.fechaNacimiento = datoFechaNacimiento;
    }
    
    public void setPasaporte(String datoPasaporte) {
    	this.pasaporte = datoPasaporte;
    }

}