
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
