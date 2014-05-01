<<<<<<< HEAD

public class Elfe  extends Thread implements NoelWorker{

	private boolean probleme;
	
	private PoolElfes pool;
	
	private long delais; //Le temps de construction du jouet
	
	private double probaProbleme;
	
	private String nom;
	
	/**
	 * Constructeur d'Elfe
	 */
	public Elfe(PoolElfes pool) {
		this.probleme = false;
		this.pool = pool;
		this.delais = (long) (Math.random()*5000);
		this.probaProbleme = Math.random();
	}
	
	public Elfe(PoolElfes pool, long delais) {
		this.probleme = false;
		this.pool = pool;
		this.delais = delais;
		this.probaProbleme = Math.random();
	}
	
	public Elfe(PoolElfes pool, long delais, double probaProblemes) {
		this.probleme = false;
		this.pool = pool;
		this.delais = delais;
		this.probaProbleme = probaProblemes;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void run() {
		while(true)
			this.travaille();
	}
	
	public void travaille(){
		//Temps de construction du jouet
		System.out.println(nom+" est en construction de jouet");
		try {
			Thread.sleep(this.delais);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.probleme = Probleme.rencontrerProbleme(this.probaProbleme);
		
		if(probleme) {
			System.out.println(nom+" rencontre un probleme");
			pool.addWorker(this);
		}
	}



	public synchronized void reveiller() {
		this.notify();
	}



	public synchronized void attendreResolution() {
		while(this.probleme) {
			try {
				System.out.println(nom+" attend la resolution a son probleme");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}




	public synchronized boolean problemeResolu() {
		return !this.probleme;
	}

	
	public void resoudreProbleme() {
		
		try {
			Thread.sleep(this.delais);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.probleme = false;
	}
	
}


class Probleme {
	
	
	static boolean rencontrerProbleme(double proba) {
		
		double random = Math.random();
		
		if(random < proba) {
			return true;
		}
		
		return false;
	}
}
=======


public class Elfe extends Thread {

	private int id;
	
	private static int cptId = 0;
	
	private SalleAttenteElfes salle;
	
	private long delais;
	
	private double probabiliteProbleme;
	
	
	//CONSTRUCTEURS
	
	/**
	 * Constructeur 1 : On ne controle ni la probabilité de rencontrer un problème, ni le temps de construction d'un jouet
	 * @param salle où les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 */
	public Elfe(SalleAttenteElfes salle) {
		this(salle, ((long) (Math.random() * 5000)), Math.random());
	}
	
	/**
	 * Constructeur 2 : On fournis un delais de construction de jouet mais on ne controle pas la probabilité de rencontrer un problème
	 * @param salle où les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 * @param delais le temps de construction d'un jouet (en millisecondes)
	 */
	public Elfe(SalleAttenteElfes salle, long delais) {
		this(salle, delais, Math.random());
	}
	
	/**
	 * Constructeur 3 : On controle la probabilité de rencontrer un probleme mais pas le temps de construction d'un jouet
	 * @param salle où les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 * @param probabiliteProbleme la probabilité de rencontrer un problème (entre 0 et 1)
	 */
	public Elfe(SalleAttenteElfes salle, double probabiliteProbleme) {
		this(salle, ((long) (Math.random() * 5000)), probabiliteProbleme);
	}
	
	/**
	 * Constructeur 4 : On a un controle total sur le temps de construction des jouets ainsi que la probabilité de rencontrer un problème.
	 * @param salle où les elfes iront attendre jusqu'a ce qu'ils puissent former des groupes
	 * @param delais le temps de construction d'un jouet (en millisecondes)
	 * @param probabiliteProbleme la probabilité de rencontrer un problème (entre 0 et 1)
	 */
	public Elfe(SalleAttenteElfes salle, long delais, double probabiliteProbleme) {
		this.salle = salle;
		this.delais = delais;
		this.probabiliteProbleme = probabiliteProbleme;
		this.id = Elfe.cptId++;
	}
	
	
	/**
	 * Va genere un probleme selon une probabilité d'en rencontrer.
	 * @return vrai si un probleme a été rencontré et faux sinon
	 */
	private boolean rencontrerProbleme() {
		double random = Math.random();
		
		if(random < this.probabiliteProbleme) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Va attendre le delais de création d'un jouet puis lorsqu'un problème est rencontré va essayer de former un groupe
	 * pour aller demander de l'aide au père noel
	 */
	private void travailler() {
		
		try {
			Thread.sleep(this.delais);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.rencontrerProbleme()) {
			//Se mettre dans la salle d'attente
			System.out.println(this.toString()+" a rencontré un problème.");
			
			this.salle.formationGroupe(this);;
			
			System.out.println("Le problème de "+this.toString()+" est résolu");
		}
		
	}
	
	
	/**
	 * Pour afficher l'identité de l'elfe dans la console
	 */
	public String toString() {
		return "Elfe "+this.id;
	}
	
	
	/**
	 * Le but unique des elfes est de travailler pour produire les jouets des enfants
	 */
	public void run() {
		while(true) {
			this.travailler();
		}
	}
	
	
}


>>>>>>> 584ea2cd158acedc2136e4cf6f9be841218b9988
