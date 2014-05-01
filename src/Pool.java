import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class Pool {
	
	private int nbWorkers = 0;
	private PereNoel pn;
	private boolean autoriseAjout;
	
	public Pool(){
		this.listeWorker = new ArrayList<NoelWorker>();
		this.autoriseAjout = true;
	}
	
	protected synchronized void addWorker (NoelWorker nw){
		System.out.println("ajout worker "+((Rene)nw).id);
		while(!autoriseAjout){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
			this.listeWorker.add(nw);
			this.nbWorkers++;
			System.out.println("Workers en attente = "+this.nbWorkers);
	}
	
	/**
	 * vide la liste des worker en attente et les réveilles
	 */
	protected synchronized void freeAllWorkers(){
		System.out.println("------- freeAllWorkers listeWorker.size ="+listeWorker.size()+"------------");
		this.notifyAll();
		this.nbWorkers = 0;
		System.out.println("------------FIN free------------");
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
