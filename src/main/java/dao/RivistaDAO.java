package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Rivista;

public class RivistaDAO {
	private final EntityManager em;

	public RivistaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Rivista r) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(r);
		transaction.commit();
	}

	public Rivista getById(long id) {
		Rivista found = em.find(Rivista.class, id);

		if (found != null) {
			System.out.println("Rivista" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}
}
