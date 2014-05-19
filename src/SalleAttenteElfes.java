/**
 * public class SalleAttenteElfes
 * 
 * C'est la salle ou vont les Elfes quand ils ont un
 * probleme.
 * SalleAttenteElfes sera partagee entre tous les elfes
 * 
 */
public class SalleAttenteElfes {

	private GroupeElfe groupe;//ref. sur le groupe d'elfes
	
	/**
	 * Constructeur
	 */
	public SalleAttenteElfes() {
		this.groupe = new GroupeElfe(this);
	}
	
	/**
	 * public GroupeElfe getGroupe()
	 * -------------------------------
	 * getter
	 * @return
	 */
	public GroupeElfe getGroupe() {
		return this.groupe;
	}
	
	/**
	 * public void formationGroupe(Elfe elfe)
	 * ----------------------------------------
	 * methode qui permet de former un groupe d'elfes.
	 * Si le groupe est plein les elfes seront mis en attente
	 * @param elfe : l'elfe de la formation
	 */
	public void formationGroupe(Elfe elfe) {
		//On va ajouter l'elfe au groupe qui ira reveiller le Pere Noel		
		while(!this.groupe.ajouterElfe(elfe)) {
			this.mettreEnAttente(elfe);
		}
	}
	
	/**
	 * private synchronized void mettreEnAttente(Elfe elfe)
	 * ----------------------------------------------------
	 * mise en attente des elfes (wait)
	 * @param elfe : elfe mis en attente
	 */
	private synchronized void mettreEnAttente(Elfe elfe) {
		System.out.println(TimeStamp.getTime()+"[SALLE  ATT.]\t"+elfe.toString()+" attend car groupe plein");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * public synchronized void groupeLibere()
	 * -------------------------------------------------
	 * Pour reveiller tout les elfes.
	 * Il n'y a normalement pas famine car le dispatcher 
	 * d'evenement java est equitable.
	 */
	public synchronized void groupeLibere() {
		System.out.println(TimeStamp.getTime()+"[SALLE  ATT.]\t"+"LE GROUPE EST LIBERE");
		notifyAll();
		System.out.println(TimeStamp.getTime()+"[SALLE  ATT.]\t"+"ON AVERTIS QUE LE GROUPE EST LIBERE");
	}
}
