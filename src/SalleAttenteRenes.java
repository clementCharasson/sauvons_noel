/**
 * public class SalleAttenteRenes
 * 
 * SalleAttenteRenes va etre le point d'attente de chaque Renne
 * apres leur retour de vacances jusqu'a la fin de la tournee de noel
 *
 */

public class SalleAttenteRenes {

	private static final int NB_RENES = 9; //Nombre de rennes necessaires pour aller reveiller le pere noel
	private int nbRenesDeRetour; //Compteur de rennes rentres de vacances
	private PereNoel pereNoel; //Le pere noel a� reveiller lorsque tous les rennes son rentres de vacances
	
	/**
	 * Constructeur
	 */
	public SalleAttenteRenes() {
		this.nbRenesDeRetour = 0; 
	}
	
	/**
	 * public void setPereNoel(PereNoel pereNoel)
	 * ------------------------------------------
	 * Le pere noel etant initialise apres la salle d'attente
	 * il faut un setteur pour le pere noel.
	 */
	public void setPereNoel(PereNoel pereNoel) {
		this.pereNoel = pereNoel;
	}
	/**
	 * public synchronized boolean auComplet()
	 * ----------------------------------------------
	 * @return : vrai si les rennes sont tous de retour
	 */
	public synchronized boolean auComplet() {
		return this.nbRenesDeRetour == SalleAttenteRenes.NB_RENES;
	}
	
	/**
	 * public synchronized void attendreNoel(Rene rene)
	 * -------------------------------------------------------
	 * Methode appelee par les rennes, si tous les rennes ne sont pas 
	 * revenus de vacances, le renne appelant se mettra en attente
	 * lorsque le dernier renne rentre, il va reveiller le pere 
	 * noel et se mettre en attente jusqu'a la fin de la tournee de noel
	 * @param rene
	 */
	public synchronized void attendreNoel(Rene rene) {
		this.nbRenesDeRetour++;
		System.out.println(TimeStamp.getTime()+"[SALLE RENE]\t"+rene.toString()+" ajouté [NB = "+this.nbRenesDeRetour+"]");
		
		if(this.auComplet()) {
			System.out.println(TimeStamp.getTime()+"[SALLE RENE]\t"+rene.toString()+" va reveiller le pere noel");
			this.pereNoel.reveille();
		}
		
		try {
			wait();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * public void tournee()
	 * ----------------------
	 * Methode appelee par le pere noel pour demarrer la tournee
	 */
	public void tournee() {
		//La tournée dure un certain temps
		try {
			Thread.sleep(10000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		this.retourTournee();
	}
	
	/**
	 * private synchronized void retourTournee()
	 * ------------------------------------------
	 * Le pere noel va debloquer tous les rennes
	 */
	private synchronized void retourTournee() {
		System.out.println(TimeStamp.getTime()+"[SALLE RENE]\t" + "Retour de la tournée, les rennes peuvent aller en vacances");
		this.nbRenesDeRetour = 0;
		notifyAll();
	}
}