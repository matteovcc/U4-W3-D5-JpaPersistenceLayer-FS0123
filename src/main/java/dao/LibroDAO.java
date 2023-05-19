package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Libro;

public class LibroDAO {
	private final EntityManager em;

	public LibroDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Libro l) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(l);
		transaction.commit();
	}

	public Libro getById(long id) {
		Libro found = em.find(Libro.class, id);

		if (found != null) {
			System.out.println("Libro" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}
}
