import java.util.ArrayList;


public class PoolRene extends Pool{
	
	public PoolRene() {
		
	}
	
	public void addWorker (Rene r){
		super.addWorker(r);
		//vérifier qu'il y a 9 renne
		//si c'est le cas réveiller le pere noel
		//sinon wait
		r.attendre();
	}
	

}
