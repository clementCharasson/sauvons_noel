/**
 * public class PereNoel extends Thread
 * 
 *	Thread representant le pere Noel
 */
public class PereNoel extends Thread {
	
	private GroupeElfe groupe;
	private SalleAttenteRennes salleRennes;
	
	/**
	 * Constructeur
	 * @param groupe : ref. sur groupe d'elfe
	 * @param salleRennes : ref. sur la salle d'attente Renne
	 */
	public PereNoel(GroupeElfe groupe, SalleAttenteRennes salleRennes) {
		this.groupe = groupe;
		this.salleRennes = salleRennes;
	}
	
	/**
	 * public synchronized void dormir()
	 * ---------------------------------
	 * Faire dormir le pere noel
	 */
	public synchronized void dormir() {
		System.out.println(TimeStamp.getTime()+"[ PERE NOEL ]\t"+"va dormir");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(TimeStamp.getTime()+"[ PERE NOEL ]\t"+"se reveille");
	}
	
	/**
	 * 	public synchronized void reveille()
	 * ------------------------------------
	 * Reveiller le pere Noel
	 */
	public synchronized void reveille(){
		notify();
	}
	
	/**
	 * private void traitement()
	 * -------------------------
	 * Quand le pere noel se reveille il regarde qui l'a reveille.
	 * Il commence par les Rennes (tournee de noel) puis les Elfes
	 * (resolution de problemes)
	 */
	private void traitement() {
		while(this.salleRennes.auComplet() || this.groupe.estPlein()) {
			if(this.salleRennes.auComplet() && this.groupe.estPlein()) {
				System.out.println(TimeStamp.getTime()+"[ PERE NOEL ]\t"+"est face a tous les rennes et tous les elfes");
			}
			//On va dans un premier temps regarder si les rennes sont au complet
			if(this.salleRennes.auComplet()) {
				System.out.println(TimeStamp.getTime()+"[ PERE NOEL ]\t"+"====demarre la TOURNEE (5 sec) ========");
				this.salleRennes.tournee();
				System.out.println(TimeStamp.getTime()+"[ PERE NOEL ]\t"+"====FIN la tournee (5 sec) ========");
			}
			
			//Puis ensuite les elfes
			if(this.groupe.estPlein()) {
				System.out.println(TimeStamp.getTime()+"[ PERE NOEL ]\t"+"====va RESOUDRE les problemes====");
				this.groupe.resoudreProbleme();
				System.out.println(TimeStamp.getTime()+"[ PERE NOEL ]\t"+"====a RESOLUT les problemes====");
			}
		}
	}
	
	/**
	 * public void run()
	 * ------------------
	 * Boucle infinie. Le pere noel ne fait que dormir ou
	 * faire un traitement (tourne ou resolution de problemes)
	 */
	public void run() {
		while(true) {
			this.dormir();
			this.traitement();
		}
	}
}
