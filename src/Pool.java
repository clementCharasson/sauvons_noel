import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class Pool {
	
	private ArrayList<NoelWorker> listeWorker;//liste d'attente
	private int nbWorkers = 0;
	private PereNoel pn;
	
	
	
	protected boolean ajoutPossible;
	
	
	
	
	public Pool(){
		this.listeWorker = new ArrayList<NoelWorker>();
		
		
		this.ajoutPossible = true;
		
		
		
	}
	
	protected synchronized void addWorker (NoelWorker nw){
		this.listeWorker.add(nw);
		this.nbWorkers++;
	}
	
	/**
	 * vide la liste des worker en attente et les réveilles
	 */
	protected   synchronized   void freeAllWorkers(){
		this.ajoutPossible = false;
		for(NoelWorker nw :this.listeWorker){
			nw.reveiller();
		}
		this.listeWorker.removeAll(this.listeWorker);
		this.nbWorkers = 0;
		this.ajoutPossible = true;
		notifyAll();
	}

	public void setPereNoel(PereNoel pn){
		this.pn = pn;
	}
	
	public ArrayList<NoelWorker> getListeWorker() {
		return listeWorker;
	}

	public int getNbWorkers() {
		return nbWorkers;
	}

	public PereNoel getPereNoel() {
		return pn;
	}
	
	
	
	/*public void rentrer(NoelWorker nw){//
		this.addWorker(nw);
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
*/
	
}
