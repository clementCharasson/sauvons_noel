import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class Pool {
	
	protected ArrayList<NoelWorker> listeWorker;//liste d'attente
	protected int nbWorkers = 0;
	protected PereNoel pn;
	
	public Pool(){
		this.listeWorker = new ArrayList<NoelWorker>();
	}
	
	public void reveil(){
		this.pn.réveille(null);
	}
	
	public void setPereNoel(PereNoel pn){
		this.pn = pn;
	}
	
	protected void addWorker (NoelWorker nw){//TODO : probablement mettre un synchronized
		this.listeWorker.add(nw);
		this.nbWorkers++;
		/*
		if(this.nbWorkers >= 3){
		}*/
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
