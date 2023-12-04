package tuti.desi.presentacion.form;

import java.util.Date;

import jakarta.validation.constraints.*;
import tuti.desi.entidades.Cliente;
import org.springframework.format.annotation.DateTimeFormat;

public class ClienteForm {
	
	@NotNull (message = "Campo Obligatorio. Debe ingresar el Nombre")
	@NotEmpty
	private String nombre;

	@NotNull (message = "Campo Obligatorio. Debe ingresar el Apellido")
	@NotEmpty
	private String apellido;

	@NotNull (message = "Campo Obligatorio. Debe ingresar el Domicilio")
	@NotEmpty
	private String domicilio;	
	
	@NotNull (message = "Campo Obligatorio. Debe ingresar el Correo Electronico")
	@NotEmpty
	@Email
    @Pattern(regexp="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")	
	private String correoElectronico;	
	
	@NotNull (message = "Campo Obligatorio. Debe ingresar la Fecha de Nacimiento")
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;	

	@NotNull (message = "Campo Obligatorio. Debe ingresar el Pasaporte")
	@NotEmpty
	private String pasaporte;	
	

	public ClienteForm(Cliente datosCliente) {
		super();
		this.nombre = datosCliente.getNombre();
		this.apellido = datosCliente.getApellido();
		this.domicilio = datosCliente.getDomicilio();
		this.correoElectronico = datosCliente.getCorreoElectronico();
		this.fechaNacimiento = datosCliente.getFechaNacimiento();
		this.pasaporte = datosCliente.getPasaporte();
	}

	public ClienteForm() {
		super();
	}

	public Cliente toPojo()
	{
		Cliente datoCliente = new Cliente();
		datoCliente.setNombre(this.nombre);
		datoCliente.setApellido(this.apellido);
		datoCliente.setDomicilio(this.domicilio);
		datoCliente.setCorreoElectronico(this.correoElectronico);
		datoCliente.setFechaNacimiento(this.fechaNacimiento);
		datoCliente.setPasaporte(this.pasaporte);
		return datoCliente;
	}

}