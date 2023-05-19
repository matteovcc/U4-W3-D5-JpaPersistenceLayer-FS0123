package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "riviste")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Rivista extends Elemento {
	private String titolo;
	private Periodicità periodicita;

	public enum Periodicità {
		SETTIMANALE, MENSILE, SEMESTRALE
	}

	public Rivista(String titolo, int anno, int pagine, Periodicità periodicita) {
		super(titolo, anno, pagine);
		this.periodicita = periodicita;
	}

}
