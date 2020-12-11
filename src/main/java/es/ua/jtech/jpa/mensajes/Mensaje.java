package es.ua.jtech.jpa.mensajes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Mensajes")
public class Mensaje {

	@Id
	@SequenceGenerator(name = "MENSAJE_ID_GENERATOR", sequenceName = "SEQ_MENSAJE", schema = "public", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENSAJE_ID_GENERATOR")
	@Column(name = "mensaje_id")
	private Integer id;
	private String texto;

	@Column(name = "fecha", columnDefinition = "DATE")
	private LocalDate fecha;


	// uni-directional many-to-one association to OrigenReclutamiento
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "autor_id", insertable = false, updatable = false)
	 private Autor autor;

	 @Column(name = "autor_id")
	 private Integer autorId;

	public Mensaje() {

	}

	public Mensaje(String texto, Autor autor) {
		this.texto = texto;
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", texto=" + texto + ", fecha=" + fecha + ", autor=" + autor + "]";
	}

}
