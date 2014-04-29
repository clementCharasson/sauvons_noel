import java.util.ArrayList;


public class PoolRene extends Pool{
	
	public PoolRene() {
		
	}
	
	//problème d'équité 4  
	public synchronized void addWorker (Rene r){
		super.addWorker(r);
		//vérifier qu'il y a 9 renne si c'est le cas réveiller le pere Noël :
		if(this.getNbWorkers() == 9){
			this.getPereNoel().reveille(r);
		}
		//sinon wait
		r.attendre();
	}
	

}
