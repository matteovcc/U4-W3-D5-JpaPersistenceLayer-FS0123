package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Elemento;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElementoDAO {
	private final EntityManager em;

	public ElementoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Elemento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public Elemento getById(String id) {
		Elemento found = em.find(Elemento.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Elemento" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public int findByIdAndDelete(String id) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query q = em.createQuery("DELETE FROM Elemento WHERE id = :id");
		q.setParameter("id", UUID.fromString(id));
		int n = q.executeUpdate();
		t.commit();
		if (n > 0) {
			log.info("cancellato");
		} else {
			log.info("non abbiamo trovato  niente da cancellare");
		}
		return n;
	}
}
