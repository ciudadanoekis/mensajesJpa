package es.ua.jtech.jpa.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.ua.jtech.jpa.mensajes.Mensaje;

public class BuscaMensajes {
	private static final String QUERY_BUSCA_MENSAJES = "SELECT m " + "FROM Mensaje m " + "WHERE m.texto LIKE : patron";
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
		EntityManager em = emf.createEntityManager();
		//No necesitamos crear una transaccion
		//No modificamos datos y no hay problemas de bloqueos
		//em.getTransaction().begin();

		System.out.println("--Buscando en los mensajes");
		String palabra = leerTexto("Introduce una palabra: ");

		String patron = "%" + palabra + "%";

		Query query = em.createQuery(QUERY_BUSCA_MENSAJES);
		query.setParameter("patron", patron);

		List<Mensaje> mensajes = query.getResultList();
		if(mensajes.isEmpty()) {
			System.out.println("No se han encontrado los mensajes");
		} else
			for(Mensaje mensaje : mensajes) {
				System.out.println(mensaje.getTexto() + " -- " + mensaje.getAutor().getNombre());
			}
		//em,getTransaction().commit();
		em.close();
		emf.close();
	}

	private static String leerTexto(String mensaje) {
		String texto;
		try {
			BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(mensaje);
			texto = in.readLine();
		} catch (IOException e) {
			texto = "error";
		}
		return texto;
	}

}
