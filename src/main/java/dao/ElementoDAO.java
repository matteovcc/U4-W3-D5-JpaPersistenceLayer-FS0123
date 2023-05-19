package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Elemento;
import entities.Libro;
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

	public Elemento findByYear(int anno) {
		TypedQuery<Elemento> q = em.createNamedQuery("Elemento.findByYear", Elemento.class);
		q.setParameter("anno", anno);
		return q.getSingleResult();
	}

	public Libro getByAuthor(String autore) {
		TypedQuery<Libro> q = em.createNamedQuery("Libro.getByAuthor", Libro.class);
		q.setParameter("autore", autore);
		return q.getSingleResult();
	}

	public Elemento findByTitolo(String titolo) {
		TypedQuery<Elemento> q = em.createNamedQuery("Elemento.findByTitolo", Elemento.class);
		q.setParameter("titolo", titolo);
		return q.getSingleResult();

	}

	public List<Elemento> findByNumeroTesseraAndPrestitoAttivo(long numerotessera) {
		TypedQuery<Elemento> q = em.createNamedQuery("Prestito.findByNumeroTesseraAndPrestitoAttivo", Elemento.class);
		q.setParameter("numerotessera", numerotessera);
		return q.getResultList();
	}
}
