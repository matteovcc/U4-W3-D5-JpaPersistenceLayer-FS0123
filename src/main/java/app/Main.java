package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.ElementoDAO;
import dao.LibroDAO;
import dao.PrestitoDAO;
import dao.RivistaDAO;
import dao.UtenteDAO;
import entities.Elemento;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Rivista.Periodicità;
import entities.Utente;
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

		// Find by ISBN (Libro l1 Harry Potter)
		System.out.println("Ricerca per id");
		ld.getById("dd0aeb08-5389-4b9a-91d8-f33df096bea7");

		// RICERCA PER ANNO
		System.out.println("Ricerca di elemento per anno");
		log.info(ed.findByYear(2000).toString());
		// RICERCA PER AUTORE
		System.out.println("Ricerca di LIBRO per autore");
		log.info(ed.getByAuthor("J.K Rowling").toString());
		// RICERCA PER TITOLO
		System.out.println("Ricerca di elemento per Titolo");
		Rivista r2 = new Rivista("Titolo rivista", 2003, 204, Periodicità.MENSILE);
//		rd.save(r2);
		log.info(ed.findByTitolo("Titolo rivista").toString());

		// Ricerca degli elementi attualmente in prestito dato un numero di tessera
		// utente

		Utente user1 = new Utente("Matteo", "Vacca", LocalDate.of(1999, 11, 04), 457);
		Utente user2 = new Utente("Gigio", "Gigietti", LocalDate.of(2001, 02, 23), 591);
		Utente user3 = new Utente("Lino", "Linetti", LocalDate.of(1992, 12, 10), 677);

//		ud.save(user3);
//		ud.save(user2);
//		ud.save(user1);
		Utente foundutente1 = ud.getById(6); // USER 1
//		if (foundutente1 != null) {
//			log.info(foundutente1.toString());
//		}
		Utente foundutente2 = ud.getById(5); // USER 2

		Rivista foundrivista2 = rd.getById("f81603a7-f35a-48a7-9f8c-61c021bfd68d"); // R2
//		if (foundrivista2 != null) {
//			log.info(foundrivista2.toString());
//		}

		Libro foundlibro1 = ld.getById("dd0aeb08-5389-4b9a-91d8-f33df096bea7");

		Prestito p1 = new Prestito(foundutente1, foundrivista2, LocalDate.of(2010, 10, 10),
				LocalDate.of(2010, 10, 10).plusDays(30), LocalDate.of(2010, 12, 30));
//		pd.save(p1);
		Prestito p2 = new Prestito(foundutente2, foundrivista2, LocalDate.of(2023, 05, 18),
				LocalDate.of(2023, 05, 18).plusDays(30), null); // prestito in corso della stessa rivista di p1
//		pd.save(p2);

		Prestito p3 = new Prestito(foundutente2, foundlibro1, LocalDate.of(2023, 05, 18),
				LocalDate.of(2023, 05, 18).plusDays(30), null);
//		pd.save(p3);

		// Trova elementi dalla tessere e attualmente in prestito
		System.out.println(
				"ELEMENTI ATTUALMENTE IN PRESTITO DELL'UTENTE(user2) CON TESSERA 591(PRESTITO p2,PRESTITO p3)");
		ed.findByNumeroTesseraAndPrestitoAttivo(591).stream().forEach(prestito -> log.info(prestito.toString()));

		// Ricerca di tutti i prestiti scaduti e non ancora restituiti
		Utente foundutente3 = ud.getById(4);
		Libro l2 = new Libro("Can't hurt me", 2019, 300, "David Goggins", "Autobiography");
//		ld.save(l2);
		Libro foundlibro2 = ld.getById("dd5ee793-7092-4dc1-9c70-95f2f45a1ed6");

		Prestito p4 = new Prestito(foundutente3, foundlibro2, LocalDate.of(2022, 01, 01),
				LocalDate.of(2022, 01, 01).plusDays(30), null);
//		pd.save(p4);

		log.info(ed.findScadutiAndNonConsegnati().toString());

		em.close();
		emf.close();
	}

}
