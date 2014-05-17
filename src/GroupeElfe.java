

/**
 * GroupeElfe sera unique et partagé entre les elfes qui verront leur problème résolu et par le Père noel
 *
 */
public class GroupeElfe {

	private static final int NB_ELFES_MAX = 3;
	
	private PereNoel pereNoel;
	
	private int nbElfesDansLeGroupe;
	
	private SalleAttenteElfes salle;
	
	public GroupeElfe(SalleAttenteElfes salle) {
		this.salle = salle;
	}
	
	
	public void setPereNoel(PereNoel pereNoel) {
		this.pereNoel = pereNoel;
	}
	
	
	public boolean estPlein() {
		return this.nbElfesDansLeGroupe == GroupeElfe.NB_ELFES_MAX;
	}
	
	
	/**
	 * Va ajouter un elfe dans le groupe qui ira reveiller le père noel
	 * @param elfe, uniquement pour afficher dans la console l'elfe qui entre dans le groupe
	 */
	public synchronized boolean ajouterElfe(Elfe elfe) {
		//Si le groupe est plein avant l'ajout de l'elfe, on ne l'ajoute pas et il devra retourner dans la salle d'attente
		if(this.estPlein()) {
			return false;
		}
		
		this.nbElfesDansLeGroupe++;
		System.out.println(TimeStamp.getTime()+"[ GRP ELFE ]\tOn ajoute un elfe dans le groupe : [NB = "+this.nbElfesDansLeGroupe+"; Elfe = "+elfe.toString()+" ]");
		
		if(this.estPlein()) {
			//On va reveiller le Pere Noel car le groupe est plein
			System.out.println(TimeStamp.getTime()+"[ GRP ELFE ]\tLe groupe est plein et va reveiller le père noel");
			this.pereNoel.reveille();
			
			//On met l'elfe en attente de resolution de son probleme
			System.out.println(TimeStamp.getTime()+"[ GRP ELFE ]\t"+elfe.toString()+" attend la resolution à son problème");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//C'est cet elfe qui aura pour mission d'aller reveiller les autres elfes qui attendent de former un groupe
			this.salle.groupeLibere();
		}
		else {
			//On met l'elfe en attente de resolution de son probleme
			System.out.println(TimeStamp.getTime()+"[ GRP ELFE ]\t"+elfe.toString()+" attend la resolution à son problème");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	/**
	 * Methode appelée par le père noel pour aider les elfes en attente de resolution de problème
	 * Le père noel va mettre du temps à résoudre le problème des elfes (Pour bien observer que personne ne peut entrer dans le groupe
	 * lorsqu'il est plein)
	 */
	public void resoudreProbleme() {
		try{
			Thread.sleep(10000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.finResolution();
	}
	
	/**
	 * Va finalement debloqué tous les elfes en attente de resolution de problème
	 */
	private synchronized void finResolution() {
		this.nbElfesDansLeGroupe = 0;
		System.out.println(TimeStamp.getTime()+"[ GRP ELFE ]\tGROUPE VIDÉ");
		notifyAll();
	}
}
