/**
 * public class GroupeElfe
 * 
 * C'est le groupe qui se forme pour reveiller le pere
 * noel. Les Elfes qui voudront former un groupe seront
 * bloques en salle d'attente.
 * GroupeElfe sera unique et partage par les elfes 
 * qui verront leur probleme resolu et le Pere noel
 *
 */
public class GroupeElfe {

	private static final int NB_ELFES_MAX = 3;//nb. elfes par groupes
	private PereNoel pereNoel;//ref. sur le pere noel
	private int nbElfesDansLeGroupe;//nb. elfes dans le groupe
	private SalleAttenteElfes salle;//ref. salle d'attente
	
	/**
	 * Constructeur.
	 * @param salle : ref. sur la salle d'attente
	 */
	public GroupeElfe(SalleAttenteElfes salle) {
		this.salle = salle;
	}
	
	/**
	 * public void setPereNoel(PereNoel pereNoel)
	 * ------------------------------------------
	 * Le pere noel etant initialise apres le groupe
	 * il faut un setteur pour le pere noel.
	 */
	public void setPereNoel(PereNoel pereNoel) {
		this.pereNoel = pereNoel;
	}
	
	/**
	 * public boolean estPlein()
	 * -----------------------------
	 * @return : vrai si le groupe est plein
	 */
	public boolean estPlein() {
		return this.nbElfesDansLeGroupe == GroupeElfe.NB_ELFES_MAX;
	}
	
	
	/**
	 * public synchronized boolean ajouterElfe(Elfe elfe)
	 * ------------------------------------------------------
	 * Va ajouter un elfe dans le groupe qui ira reveiller le pere noel
	 * @param elfe, uniquement pour afficher dans la console l'elfe qui entre dans le groupe
	 */
	public synchronized boolean ajouterElfe(Elfe elfe) {
		//Si le groupe est plein avant l'ajout de l'elfe, on ne l'ajoute pas et il devra retourner dans la salle d'attente
		if(this.estPlein()) {
			return false;
		}
		
		this.nbElfesDansLeGroupe++;
		System.out.println(TimeStamp.getTime()+"[ GRP  ELFE ]\tOn ajoute un elfe dans le groupe : [NB = "+this.nbElfesDansLeGroupe+"; Elfe = "+elfe.toString()+" ]");
		
		if(this.estPlein()) {
			//On va reveiller le Pere Noel car le groupe est plein
			System.out.println(TimeStamp.getTime()+"[ GRP  ELFE ]\tLe groupe est plein et va reveiller le pere noel");
			this.pereNoel.reveille();
			
			//On met l'elfe en attente de resolution de son probleme
			System.out.println(TimeStamp.getTime()+"[ GRP  ELFE ]\t"+elfe.toString()+" attend la resolution à son probleme");
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
			System.out.println(TimeStamp.getTime()+"[ GRP  ELFE ]\t"+elfe.toString()+" attend la resolution de son probleme");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	/**
	 * public void resoudreProbleme()
	 * -----------------------------------
	 * Methode appelee par le pere noel pour aider les elfes en 
	 * attente de resolution de probleme.
	 * Le pere noel va mettre du temps a resoudre le probleme des 
	 * elfes (Pour bien observer que personne ne peut entrer dans 
	 * le groupe lorsqu'il est plein).
	 */
	public void resoudreProbleme() {
		try{
			Thread.sleep(5000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.finResolution();
	}
	
	/**
	 * private synchronized void finResolution()
	 * -----------------------------------------
	 * Va finalement debloque tous les elfes en attente de resolution de probleme
	 */
	private synchronized void finResolution() {
		this.nbElfesDansLeGroupe = 0;
		System.out.println(TimeStamp.getTime()+"[ GRP  ELFE ]\tGROUPE VIDÉ");
		notifyAll();
	}
}
