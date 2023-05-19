package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "prestiti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prestito {
	@Id
	@GeneratedValue
	private long id;
	private LocalDate inizioprestito;
	private LocalDate restituzioneprevista;
	private LocalDate restituzioneeffettiva;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "elemento_id")
	private Elemento elementoprestato;

	public Prestito(Utente utente, Elemento elementoprestato, LocalDate inizioprestito, LocalDate restituzioneprevista,
			LocalDate restituzioneeffettiva) {
		super();
		this.utente = utente;
		this.elementoprestato = elementoprestato;
		this.inizioprestito = inizioprestito;
		this.restituzioneprevista = restituzioneprevista;
		this.restituzioneeffettiva = restituzioneeffettiva;
	}

}