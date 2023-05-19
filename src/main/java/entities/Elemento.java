package entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "elementi_libreria")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Elemento {
	@Id
	@GeneratedValue
	private UUID id;
	private String titolo;
	private int anno;
	private int pagine;

	@OneToMany(mappedBy = "elementoprestato")
	private List<Prestito> prestiti;

	public Elemento(String titolo, int anno, int pagine) {
		super();
		this.titolo = titolo;
		this.anno = anno;
		this.pagine = pagine;
	}

	public Elemento(String titolo, int anno, int pagine, List<Prestito> prestiti) {
		super();
		this.titolo = titolo;
		this.anno = anno;
		this.pagine = pagine;
		this.prestiti = prestiti;
	}

}
