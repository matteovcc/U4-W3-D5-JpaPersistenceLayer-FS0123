package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.ElementoDAO;
import dao.LibroDAO;
import dao.PrestitoDAO;
import dao.RivistaDAO;
import dao.UtenteDAO;
import entities.Elemento;
import entities.Libro;
import entities.Rivista;
import entities.Rivista.Periodicità;
import lombok.extern.slf4j.Slf4j;
import util.JpaUtil;

@Slf4j
public class Main {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		LibroDAO ld = new LibroDAO(em);
		ElementoDAO ed = new ElementoDAO(em);
		RivistaDAO rd = new RivistaDAO(em);
		UtenteDAO ud = new UtenteDAO(em);
		PrestitoDAO pd = new PrestitoDAO(em);

		Libro l1 = new Libro("Harry Potter", 2000, 350, "J.K Rowling", "Fantasy");
//		ld.save(l1);
		Rivista r1 = new Rivista("Vanity Fair", 2010, 70, Periodicità.SETTIMANALE);
//		rd.save(r1);

		// -----FIND BY UUID(isbn) AND DELETE----- ///
		Elemento elementodacancellare = new Elemento("Titolo", 2006, 250);
//		ed.save(elementodacancellare);
		// uuid : a0dd7eb8-869a-420f-ab8d-587355618a54
		ed.findByIdAndDelete("a0dd7eb8-869a-420f-ab8d-587355618a54");
		// Find by ISBN Libro l1 Harry Potter
		ld.getById("dd0aeb08-5389-4b9a-91d8-f33df096bea7");
		// RICERCA PER ANNO
		log.info(ed.findByYear(2000).toString());

	}

}
