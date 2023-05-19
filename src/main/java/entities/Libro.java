package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "libri")
@Getter
@Setter
@ToString
@NoArgsConstructor
@NamedQuery(name = "Libro.getByAuthor", query = "SELECT l from Libro l WHERE l.autore = :autore")
public class Libro extends Elemento {
	private String autore;
	private String genere;

	public Libro(String titolo, int anno, int pagine, String autore, String genere) {
		super(titolo, anno, pagine);
		this.autore = autore;
		this.genere = genere;
	}

}
