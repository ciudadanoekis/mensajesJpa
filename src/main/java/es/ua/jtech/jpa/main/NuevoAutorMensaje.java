package es.ua.jtech.jpa.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.ua.jtech.jpa.mensajes.Autor;
import es.ua.jtech.jpa.mensajes.Mensaje;

public class NuevoAutorMensaje {
	public static void main(String[] args) {
		Autor autor;

		//Creamos la factoria de entity manager y un entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
		EntityManager em = emf.createEntityManager();
		//Marcamos el comienzo de la transaccion
		em.getTransaction().begin();

		//Pedimos los datos del autor
		String email = leerTexto("introduce el correo electronico: ");
		String nombre = leerTexto("Introduce nombre: ");
		autor = new Autor(nombre, email);

		//Lo persistimos en la base de datos
		em.persist(autor);
		System.out.println("Identificador del autor : " + autor.getId());

		//Creamos el mensaje
		String mensajeStr = leerTexto("Introduce mensaje: ");
		Mensaje mens = new Mensaje(mensajeStr, autor);

		//Establecemos los campos
		mens.setFecha(LocalDate.now());
		//Y lo guardamos en la base de datos
		em.persist(mens);

		Integer idMensaje = mens.getId();
		System.out.println("Identificador del mensaje: " + idMensaje);

		//Cerramos la transaccion y el entity manager
		em.getTransaction().commit();
		em.close();
	}

	private static String leerTexto(String mensaje) {
		String texto;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(mensaje);
			texto = in.readLine();
		} catch (IOException e) {
			texto = "error";
		}
		return texto;
	}

}
