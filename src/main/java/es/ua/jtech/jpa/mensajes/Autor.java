package es.ua.jtech.jpa.mensajes;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "Autor")
public class Autor {

	@Id
	@GeneratedValue
	@Column(name = "autor_id")
	private Long id;
	private String correo;
	private String nombre;
	@OneToMany(mappedBy= "autor", cascade = CascadeType.ALL)
	private Set<Mensaje> mensajes = new HashSet<Mensaje>();

	public Autor() {

	}

	public Autor(String correo) {
		this.correo = correo;
	}
	public Autor(String nombre, String correo) {
		this.nombre = nombre;
		this.correo = correo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
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
		Autor other = (Autor) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", correo=" + correo + ", nombre=" + nombre + "]";
	}


}
