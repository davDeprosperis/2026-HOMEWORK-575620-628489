package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.*;
/**
 * Classe che modella la borsa del giocatore.
 * Permette di aggiungere e rimuovere attrezzi, tenendo conto
 * di un peso massimo trasportabile.
 *
 * @author  docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see    Attrezzo
 * @version 3.0
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;

	/**
     *Crea una borsa con una capacita' di carico default.
     */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	/**
     * Crea una borsa con una capacita' di carico definita.
     * * @param pesoMax Il limite di peso massimo trasportabile nella borsa
     */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>(); 
	}
/**
 * Aggiunge, se possibile, un attrezzo alla borsa
 * 
 * @param attrezzo
 * @return true se l'attrezzo è stato aggiunto, false altrimenti
 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	
/**
 * 
 * @return peso massimo trasportabile dalla borsa
 */
	public int getPesoMax() {
		return pesoMax;
	}
/**
 * Ha lo scopo di ritornare l'attrezzo avente il nome offerto come parametro
 * @param nomeAttrezzo Il nome dell'attrezzo
 * @return l'attrezzo avente nome cercato, se presente, altrimenti null
 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
/**
 * Ha lo scopo di ritornare il peso totale degli oggetti presenti nella borsa
 * @return peso della borsa
 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi.values()) {
			peso += a.getPeso();
		}
		return peso;
	}
/**
 * Verifica che la borsa sia vuota
 * 
 * @return true se la borsa e'vuota, false altrmenti
 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
/**
 * Cerca un oggetto all'interno della borsa
 * @param nomeAttrezzo Il nome dell'attrezzo da cercare
 * @return true se l'attrezzo e' presente nella borsa, false altrimenti
 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	
	/**
	 * Cerca di rimuovere un determinato attrezzo avente nome fornito come parametro
	 * ritornando eventualmente l'attrezzo appena rimosso.
	 * @param nomeAttrezzo Il nome dell'attrezzo che si vuole rimuovere
	 * @return l'attrezzo appena rimosso se la rimozione e' avvenuta, null altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	/**
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e quindi,
	 * a parità di peso, per nome.
	 * @return lista ordinata di attrezzi
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> risultato = new ArrayList<>(this.attrezzi.values());
		
		// Classe anonima per definire il criterio di ordinamento
		Comparator<Attrezzo> cmpPesoNome = new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if (a1.getPeso() == a2.getPeso()) {
					return a1.getNome().compareTo(a2.getNome());
				}
				return a1.getPeso() - a2.getPeso();
			}
		};
		
		Collections.sort(risultato, cmpPesoNome);
		return risultato;
	}

	/**
	 * Restituisce l'insieme degli attrezzi nella borsa ordinati per nome.
	 * @return SortedSet ordinato di attrezzi
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		// Passiamo un Comparator direttamente al costruttore del TreeSet
		SortedSet<Attrezzo> risultato = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		
		risultato.addAll(this.attrezzi.values());
		return risultato;
	}

	/**
	 * Restituisce una mappa che associa un intero (rappresentante un peso) 
	 * con l'insieme degli attrezzi di tale peso.
	 * @return Mappa raggruppata per peso
	 */
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> risultato = new HashMap<>();
		
		for (Attrezzo a : this.attrezzi.values()) {
			Integer peso = a.getPeso();
			// Se il peso è già nella mappa, aggiungiamo l'attrezzo al Set esistente
			if (risultato.containsKey(peso)) {
				risultato.get(peso).add(a);
			} else {
				// Altrimenti creiamo un nuovo Set, ci mettiamo l'attrezzo e inseriamo nella mappa
				Set<Attrezzo> nuovoSet = new HashSet<>();
				nuovoSet.add(a);
				risultato.put(peso, nuovoSet);
			}
		}
		return risultato;
	}
	
	/**
	 * Restituisce l'insieme degli attrezzi nella borsa ordinati per peso e quindi,
	 * a parità di peso, per nome.
	 * @return SortedSet ordinato di attrezzi
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> risultato = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if (a1.getPeso() == a2.getPeso()) {
					return a1.getNome().compareTo(a2.getNome());
				}
				return a1.getPeso() - a2.getPeso();
			}
		});
		
		risultato.addAll(this.attrezzi.values());
		return risultato;
	}
	
	/**
	 * Restituisce una rappresentazione testuale del contenuto della borsa,
	 * stampando gli attrezzi raggruppati per peso.
	 * @return il contenuto della borsa raggruppato sotto forma di stringa
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (").append(this.getPeso()).append("kg/").append(this.getPesoMax()).append("kg): ");
			
			
			Map<Integer, Set<Attrezzo>> raggruppato = this.getContenutoRaggruppatoPerPeso();
			
			for (Map.Entry<Integer, Set<Attrezzo>> entry : raggruppato.entrySet()) {
				s.append("(").append(entry.getKey()).append(", {");
				
				Iterator<Attrezzo> it = entry.getValue().iterator();
				while (it.hasNext()) {
					s.append(it.next().getNome());
					if (it.hasNext()) {
						s.append(", ");
					}
				}
				s.append("}) ");
			}
		} else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}
}
