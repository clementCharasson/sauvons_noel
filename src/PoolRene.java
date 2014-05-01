import java.util.ArrayList;


public class PoolRene extends Pool{
	
	public PoolRene() {
		
	}
	
	//problème d'équité 4  
	public synchronized void addWorker (Rene r){
		System.out.println("ajout worker rene "+r.id);
		super.addWorker(r);
		//vérifier qu'il y a 9 rene si c'est le cas réveiller le pere Noël :
		if(this.getNbWorkers() == 9){
			System.out.println("addWorker - je réveille le pere noel");
			this.getPereNoel().reveille(r);//le dernier rène réveille le pere noël
		}else{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
