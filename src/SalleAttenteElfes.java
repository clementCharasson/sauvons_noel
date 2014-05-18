/**
 * public class SalleAttenteElfes
 * 
 * C'est là salle ou vont les Elfes quand ils ont un
 * problème.
 * SalleAttenteElfes sera partagée entre tous les elfes
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
	 * getteur
	 * @return
	 */
	public GroupeElfe getGroupe() {
		return this.groupe;
	}
	
	/**
	 * public void formationGroupe(Elfe elfe)
	 * ----------------------------------------
	 * méthode qui permet de former un groupe d'elfes.
	 * Si le groupe est plein les elfes seront mis en attentes
	 * @param elfe : l'elfe de la formation
	 */
	public void formationGroupe(Elfe elfe) {
		//On va ajouter l'elfe au groupe qui ira reveiller le Père Noel		
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
		System.out.println(TimeStamp.getTime()+"[SALLE ATT.]\t"+elfe.toString()+" attend car groupe plein");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * public synchronized void groupeLibere()
	 * -------------------------------------------------
	 * Pour réveillé tout les elfes et forment un groupe.
	 * Il normalement n'y a pas famine car le dispatcher 
	 * dévennement java est équitable.
	 */
	public synchronized void groupeLibere() {
		System.out.println(TimeStamp.getTime()+"[SALLE ATT.]\t"+"LE GROUPE EST LIBERE");
		notifyAll();
		System.out.println(TimeStamp.getTime()+"[SALLE ATT.]\t"+"ON AVERTIS QUE LE GROUPE EST LIBERE");
	}
}
