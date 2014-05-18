/**
 * public class SalleAttenteRenes
 * 
 * SalleAttenteRenes va être le point d'attente de chaque Renes 
 * après leur retour de vacances jusqu'a la fin de la tournée de noel
 *
 */

public class SalleAttenteRenes {

	private static final int NB_RENES = 9; //Nombre de renes necessaire pour aller réveiller le père noel
	private int nbRenesDeRetour; //Compteur de renes rentré de vacances
	private PereNoel pereNoel; //Le père noel à réveiller lorsque tous les renes son rentré de vacances
	
	/**
	 * Constructeur
	 */
	public SalleAttenteRenes() {
		this.nbRenesDeRetour = 0; 
	}
	
	/**
	 * public void setPereNoel(PereNoel pereNoel)
	 * ------------------------------------------
	 * Le père noël étant initialisé après la salle d'attente
	 * il faut un setteur pour le pere noël.
	 */
	public void setPereNoel(PereNoel pereNoel) {
		this.pereNoel = pereNoel;
	}
	/**
	 * public synchronized boolean auComplet()
	 * ----------------------------------------------
	 * @return : vrai si les rènes sont tous de retour
	 */
	public synchronized boolean auComplet() {
		return this.nbRenesDeRetour == SalleAttenteRenes.NB_RENES;
	}
	
	/**
	 * public synchronized void attendreNoel(Rene rene)
	 * -------------------------------------------------------
	 * Methode appelée par les rènes, si tous les rènes ne sont pas 
	 * revenu de vacances, le rène appelant se mettra en attente
	 * lorsque le dernier rène rentre, il va reveiller le père 
	 * noel et se mettre en attente jusqu'a la fin de la tournée de noel
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
	 * Methode appelée par le père noel pour demarrer la tournée
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
	 * Le père noel va debloquer tous les renes
	 */
	private synchronized void retourTournee() {
		System.out.println(TimeStamp.getTime()+"[SALLE RENE]\t"+"Retour de la tournée, les renes peuvent aller en vacances");
		this.nbRenesDeRetour = 0;
		notifyAll();
	}
}