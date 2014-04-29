import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class Pool {
	
	private ArrayList<NoelWorker> listeWorker;//liste d'attente
	private int nbWorkers = 0;
	private PereNoel pn;
	private boolean autoriseAjout;
	
	public Pool(){
		this.listeWorker = new ArrayList<NoelWorker>();
		this.autoriseAjout = true;
	}
	
	protected synchronized void addWorker (NoelWorker nw){
		while(!autoriseAjout);
		this.listeWorker.add(nw);
		this.nbWorkers++;
	}
	
	/**
	 * vide la liste des worker en attente et les réveilles
	 */
	protected void freeAllWorkers(){
		System.out.println("DEBUG - freeAllWorkers listeWorker.size ="+listeWorker.size());
		this.autoriseAjout = false;
		for(NoelWorker nw :this.listeWorker){
			System.out.println("free 1 workers");
			nw.reveiller();
		}
		this.listeWorker.removeAll(this.listeWorker);
		this.nbWorkers = 0;
		this.autoriseAjout = true;
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
