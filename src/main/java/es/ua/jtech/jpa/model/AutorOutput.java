package es.ua.jtech.jpa.model;

import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import com.paradigma.intranet.microservices.architecture.model.mapper.MappeableFromTo;

import es.ua.jtech.jpa.entities.Autor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorOutput implements MappeableFromTo<Autor> {
	@NotNull
	@ApiModelProperty(value = "Identificador del autor", required = true)
	private Integer id;

	@NotNull
	@ApiModelProperty(value = "Correo del autor", required = true)
	private String correo;

	@NotNull
	@ApiModelProperty(value = "Nombre del autor", required = true)
	private String nombre;

	public static AutorOutput of(Autor autor) {
		AutorOutput result = null;

		if(autor != null && Hibernate.isInitialized(autor)) {
			result = new AutorOutput();
			result.from(autor);
		}
		return result;
	}

	@Override
	public void from(Autor origin) {
		id = origin.getId();
		correo = origin.getCorreo();
		nombre = origin.getCorreo();
	}

	@Override
	public Autor to() {
		// No se mapea
		return null;
	}
}
