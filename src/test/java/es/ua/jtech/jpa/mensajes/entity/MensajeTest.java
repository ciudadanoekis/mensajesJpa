package es.ua.jtech.jpa.mensajes.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paradigma.intranet.microservices.architecture.test.AbstractBaseTest;

import es.ua.jtech.jpa.entities.Autor;
import es.ua.jtech.jpa.entities.Mensaje;

public class MensajeTest  extends AbstractBaseTest {
	private static final Integer ID = 1;
	private static final Integer NUM = 8;
	private static final String TEXTO = "texto del mensaje";
	private static final LocalDate FECHA = LocalDate.now();
	private static final Integer AUTOR_ID = 5;
	private static final Autor AUTOR = new Autor();


	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Test
	public void mensajeOKGetterSetters() {
		Mensaje mensaje = getMensaje(ID);

		assertThat(mensaje.getId()).isEqualTo(ID);
		assertThat(mensaje.getNum()).isEqualTo(NUM);
		assertThat(mensaje.getTexto()).isEqualTo(TEXTO);
		assertThat(mensaje.getFecha()).isEqualTo(FECHA);
		assertThat(mensaje.getAutorId()).isEqualTo(AUTOR_ID);
		assertThat(mensaje.getAutor()).isEqualTo(AUTOR);

	}

	private Mensaje getMensaje(Integer id) {
		Mensaje mensaje = new Mensaje();

		mensaje.setId(id);
		mensaje.setNum(NUM);
		mensaje.setTexto(TEXTO);
		mensaje.setFecha(FECHA);
		mensaje.setAutorId(AUTOR_ID);
		mensaje.setAutor(AUTOR);
		return mensaje;
	}

}
