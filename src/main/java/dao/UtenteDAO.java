package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Utente;

public class UtenteDAO {
	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Utente u) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(u);
		transaction.commit();
	}

	public Utente getById(long id) {
		Utente found = em.find(Utente.class, id);

		if (found != null) {
			System.out.println("Utente" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}
}
