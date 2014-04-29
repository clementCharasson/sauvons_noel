import java.util.ArrayList;


public class PoolRene extends Pool{
	
	public PoolRene() {
		
	}
	
	//problème d'équité 4  
	public void addWorker (Rene r){
		super.addWorker(r);
		//vérifier qu'il y a 9 renne si c'est le cas réveiller le pere Noël :
		if(this.getNbWorkers() == 9){
			System.out.println("addWorker - je réveille le pere noel");
			this.getPereNoel().reveille(r);//le dernier rène réveille le pere noël
		}else
			//sinon wait
			r.attendre();
	}
	

}
