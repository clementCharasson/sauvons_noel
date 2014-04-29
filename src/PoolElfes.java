import java.util.ArrayList;


public class PoolElfes extends Pool{

	private static int NB_ELFES_POOL = 3;
	
	
	public PoolElfes() {
		super();
	}
	
	
	public void addWorker (Elfe elfe){
		
		synchronized(this) {
			//On va verifier si le groupe d'elfe n'est pas deja pein
			while(this.getNbWorkers() > PoolElfes.NB_ELFES_POOL - 1) {
				try {
					System.out.println("La pool est pleine");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			while(!this.ajoutPossible) {
				try {
					System.out.println("Impossible d'ajouter un elfe, la pool est en train de se vider");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//On ajoute l'elfe dans la pool d'elfes
			System.out.println("On ajoute un elfe dans la pool");
			super.addWorker(elfe);
		}
				
		if(this.getNbWorkers() < PoolElfes.NB_ELFES_POOL) {
			while(!elfe.problemeResolu()) {
				elfe.attendreResolution();
			}
		}
		else {
			this.getPereNoel().reveille(elfe);
			while(!elfe.problemeResolu()) {
				elfe.attendreResolution();
			}
		}
	}


	public void resoudreLesProblemes() {
		for(int i = 0; i < this.getListeWorker().size(); i++) {
			Elfe e = (Elfe) this.getListeWorker().get(i);
			e.resoudreProbleme();
			System.out.println("Le pere noel a resolu le probleme de "+e.getNom());
		}
		
		this.freeAllWorkers();
	}
	
}
