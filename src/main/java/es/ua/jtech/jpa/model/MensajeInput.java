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
public class MensajeInput implements MappeableFromTo<Mensaje> {


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

	public static MensajeInput of(Mensaje mensaje) {
		MensajeInput result = null;
		if (mensaje != null && Hibernate.isInitialized(mensaje)) {
			result = new MensajeInput();
			result.from(mensaje);
		}
		return result;

	}

	@Override
	public void from(Mensaje origin) {

		num = origin.getNum();
		texto = origin.getTexto();
		fecha = origin.getFecha();
		autorId = origin.getAutorId();
	}

	@Override
	public Mensaje to() {
		final Mensaje result = new Mensaje();

		result.setNum(num);
		result.setTexto(texto);
		result.setFecha(fecha);
		result.setAutorId(autorId);

		return result;
	}
}
