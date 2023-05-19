package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue
	private long id;
	private long numerotessera;
	private String nome;
	private String cognome;
	private LocalDate datadinascita;

	@OneToMany(mappedBy = "utente")
	private List<Prestito> prestiti;

	public Utente(String nome, String cognome, LocalDate datadinascita, long numerotessera) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.datadinascita = datadinascita;
		this.numerotessera = numerotessera;
	}

}
