import java.util.HashSet;
import java.util.Set;


public class Réveille {
	private Set<Elfe> elfeAttente;//nb elfes en attente
	private Set<Rène> rèneAttente;//nb de rènes en attente
	
	public Réveille() {
		elfeAttente = new HashSet<Elfe>();
		rèneAttente = new HashSet<Rène>();
	}
}
