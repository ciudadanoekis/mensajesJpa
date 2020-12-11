package es.ua.jtech.jpa.mensajes;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

class PersistenceTest {

	@Test
	public void createEntityManagerTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
		em.close();
		emf.close();
	}
}
