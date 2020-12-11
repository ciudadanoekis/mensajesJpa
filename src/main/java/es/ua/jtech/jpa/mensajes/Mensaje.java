package es.ua.jtech.jpa.mensajes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Mensaje")
public class Mensaje {

	@Id
	@GeneratedValue
	@Column(name = "mensaje_id")
	private Long id;
	private String texto;
	private Date fecha;
	@ManyToOne
	private Autor autor;

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
