package tuti.desi.entidades;

import jakarta.persistence.*;

@Entity
public class Pasaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_pasaje")
    private Long nroPasaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nro_vuelo")
    private Vuelo vuelo;

    private int nroAsiento;

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;	
	}

	public void setNroAsiento(int nroAsiento) {
		this.nroAsiento = nroAsiento;
	}
	
	public int getNroAsiento() {
		return this.nroAsiento;
	}
	
}