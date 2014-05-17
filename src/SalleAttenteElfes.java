


/**
 * SalleAttenteElfes sera partagée entre tous les elfes
 */
public class SalleAttenteElfes {

	private GroupeElfe groupe;
	
	public SalleAttenteElfes() {
		this.groupe = new GroupeElfe(this);
	}
	
	
	public GroupeElfe getGroupe() {
		return this.groupe;
	}
	
	
	public void formationGroupe(Elfe elfe) {
		//On va ajouter l'elfe au groupe qui ira reveiller le Père Noel		
		while(!this.groupe.ajouterElfe(elfe)) {
			this.mettreEnAttente(elfe);
		}
	}
	
	
	private synchronized void mettreEnAttente(Elfe elfe) {
		System.out.println(TimeStamp.getTime()+"[SALLE ATT.]\t"+elfe.toString()+" attend car groupe plein");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public synchronized void groupeLibere() {
		System.out.println(TimeStamp.getTime()+"[SALLE ATT.]\t"+"LE GROUPE EST LIBERE");
		notifyAll();
		System.out.println(TimeStamp.getTime()+"[SALLE ATT.]\t"+"ON AVERTIS QUE LE GROUPE EST LIBERE");
	}
}
