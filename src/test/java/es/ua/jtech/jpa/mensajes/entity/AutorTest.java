package es.ua.jtech.jpa.mensajes.entity;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.paradigma.intranet.microservices.architecture.test.AbstractBaseTest;

import es.ua.jtech.jpa.entities.Autor;
import es.ua.jtech.jpa.entities.Mensaje;


class AutorTest extends AbstractBaseTest {
	private static final Integer ID = 1;
	private static final String EMAIL = "elcorreo@correo.com";
	private static final String NAME = "el nombre";

	@Override
	protected void initialize() {
		// Nothing to do
	}

	@Test
	public void autorOKGettersSetters() {
		Autor autor = getAutor(ID);

		assertThat(autor.getId()).isEqualTo(ID);
		assertThat(autor.getCorreo()).isEqualTo(EMAIL);
		assertThat(autor.getNombre()).isEqualTo(NAME);
		assertThat(autor.getMensajes()).hasSize(0);
	}

	private Autor getAutor(Integer id2) {
		Autor autor = new Autor();
		autor.setId(ID);
		autor.setCorreo(EMAIL);
		autor.setNombre(NAME);
		autor.setMensajes(new HashSet<Mensaje>());
		return autor;
	}

}