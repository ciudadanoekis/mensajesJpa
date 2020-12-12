package es.ua.jtech.jpa.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import com.paradigma.intranet.microservices.architecture.model.mapper.MappeableFromTo;

import es.ua.jtech.jpa.entities.Mensaje;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeOutput implements MappeableFromTo<Mensaje> {

	@NotNull
	@ApiModelProperty(value = "Identificador del mensaje", required = true)
	private Integer id;

	@NotNull
	@ApiModelProperty(value = "Numero del mensaje", required = true)
	private Integer num;

	@NotNull
	@ApiModelProperty(value = "Texto del mensaje", required = true)
	private String texto;

	@NotNull
	@ApiModelProperty(value = "Fecha de creacion del mensaje", required = true)
	private LocalDate fecha;

	@NotNull
	@ApiModelProperty(value = "Identificador del autor del mensaje", required = true)
	private Integer autorId;

	public static MensajeOutput of(Mensaje mensaje) {
		MensajeOutput result = null;
		if (mensaje != null && Hibernate.isInitialized(mensaje)) {
			result = new MensajeOutput();
			result.from(mensaje);
		}
		return result;

	}

	@Override
	public void from(Mensaje origin) {

		id = origin.getId();
		num = origin.getNum();
		texto = origin.getTexto();
		fecha = origin.getFecha();
		autorId = origin.getAutorId();
	}

	@Override
	public Mensaje to() {
		// no se mapea
		return null;
	}
}
