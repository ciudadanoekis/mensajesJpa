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

public class NuevoMensaje {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		System.out.println("--Añadiendo mensaje de usuario");

		Long idAutor = Long.valueOf(leerTexto("introduce identificador de usuario: "));

		Autor autor = em.find(Autor.class, idAutor);
		if(autor == null) {
			System.out.println("Usuario no existente");
		} else {
			System.out.println("Usuario " + autor.getNombre());
			String mensaStr = leerTexto("Introduce mensaje: ");
			Mensaje mens = new Mensaje(mensaStr, autor);
			mens.setFecha(LocalDate.now());
			em.persist(mens);
			System.out.println("identificador del mensaje: " + mens.getId());
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	private static String leerTexto(String mensaje) {
		String texto;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			texto = in.readLine();
		} catch (IOException e) {
			texto = "Error";
		}
		return texto;
	}

}
