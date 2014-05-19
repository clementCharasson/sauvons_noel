/**
 * public class Elfe extends Thread
 *
 *Thread representant les Elfes
 */
public class Elfe extends Thread {

	private int id;//identifiant
	private static int cptId = 0;//compteur d'id
	private SalleAttenteElfes salle;//ref. salle d'attente des elfes
	private long delais;//temps de fabrication d'un jouet
	private double probabiliteProbleme;//proba d'avoir un probleme
	
	
	//CONSTRUCTEURS
	
	/**
	 * Constructeur 1 : On ne controle ni la probabilite de rencontrer un probleme, ni le temps de construction d'un jouet
	 * @param salle ou les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 */
	public Elfe(SalleAttenteElfes salle) {
		this(salle, ((long) (Math.random() * 5000)), Math.random());
	}
	
	/**
	 * Constructeur 2 : On fournit un delai de construction de jouet mais on ne controle pas la probabilite de rencontrer un probleme
	 * @param salle ou les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 * @param delais le temps de construction d'un jouet (en millisecondes)
	 */
	public Elfe(SalleAttenteElfes salle, long delais) {
		this(salle, delais, Math.random());
	}
	
	/**
	 * Constructeur 3 : On controle la probabilite de rencontrer un probleme mais pas le temps de construction d'un jouet
	 * @param salle ou les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 * @param probabiliteProbleme la probabilite de rencontrer un probleme (entre 0 et 1)
	 */
	public Elfe(SalleAttenteElfes salle, double probabiliteProbleme) {
		this(salle, ((long) (Math.random() * 5000)), probabiliteProbleme);
	}
	
	/**
	 * Constructeur 4 : On a un controle total sur le temps de construction des jouets ainsi que la probabilite de rencontrer un probleme.
	 * @param salle ou les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 * @param delais le temps de construction d'un jouet (en millisecondes)
	 * @param probabiliteProbleme la probabilite de rencontrer un probleme (entre 0 et 1)
	 */
	public Elfe(SalleAttenteElfes salle, long delais, double probabiliteProbleme) {
		this.salle = salle;
		this.delais = delais;
		this.probabiliteProbleme = probabiliteProbleme;
		this.id = Elfe.cptId++;
	}
	
	
	/**
	 * private boolean rencontrerProbleme()
	 * --------------------------------------
	 * Genere un probleme selon une probabilite.
	 * @return vrai si un probleme a ete rencontre et faux sinon
	 */
	private boolean rencontrerProbleme() {
		double random = Math.random();
		
		if(random < this.probabiliteProbleme) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * private void travailler()
	 * --------------------------------
	 * Va attendre le delai de creation d'un jouet puis 
	 * si un probleme est rencontre va essayer de former 
	 * un groupe pour aller demander de l'aide au pere noel
	 */
	private void travailler() {
		System.out.println(TimeStamp.getTime()+"[   ELFE   ]\t"+this.toString()+" fabrique jouet ("+this.delais/1000L+" sec)");
		try {
			Thread.sleep(this.delais);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.rencontrerProbleme()) {
			//Se mettre dans la salle d'attente
			System.out.println(TimeStamp.getTime()+"[   ELFE   ]\t"+this.toString()+" a rencontre un probleme.");
			
			this.salle.formationGroupe(this);;
			
			System.out.println(TimeStamp.getTime()+"[   ELFE   ]\t"+"Le probleme de "+this.toString()+" est resolu");
		}
		System.out.println(TimeStamp.getTime()+"[   ELFE   ]\t"+this.toString()+" a terminer le jouet");
	}
	
	
	/**
	 * public String toString()
	 * -------------------------
	 * Pour afficher l'identite de l'elfe dans la console
	 */
	public String toString() {
		return "Elfe("+this.id+")";
	}
	
	
	/**
	 * public void run()
	 * -------------------
	 * Boucle infinie.
	 * Le but unique des elfes est de travailler 
	 * pour produire les jouets des enfants. :-)
	 */
	public void run() {
		while(true) {
			this.travailler();
		}
	}
	
}
