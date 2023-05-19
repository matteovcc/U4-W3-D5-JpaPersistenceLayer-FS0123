package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Prestito;

public class PrestitoDAO {
	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Prestito p) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(p);
		transaction.commit();
	}

	public Prestito getById(long id) {
		Prestito found = em.find(Prestito.class, id);

		if (found != null) {
			System.out.println("Prestito" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}
}
