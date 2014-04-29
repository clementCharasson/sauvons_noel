
public class PereNoel extends Thread{
	
	private PoolRene poolRene ;

	PereNoel(PoolRene poolRene){
		this.poolRene = poolRene;
	}
	
	
	public void  run(){
		while(true){
			this.dormir();
		}
	}
	
	public synchronized void dormir(){
		System.out.println("Pere Noël - dort");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void reveille(NoelWorker nw){
		System.out.println("Pere Noël - réveillé");
		this.notify();
		if(nw instanceof Rene){
			this.tourne();
			this.poolRene.freeAllWorkers();
		}else{//c'est des elfes
			;//
		}
	}
	
	//pour les Rènes
	public void tourne(){//la tournée du pere Noel
		System.out.println("Père noël - C'est la tournée de Noël");
		try {
			Thread.sleep(3000);//tournée
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Père noël - Fin de la tournée du Père Noël");
	}
	
	//pour les Elfes
	public void resoudreProbleme(){
		System.out.println("le Père noël - résoud des problènes");
		
		//prendre le temps prévu pour la tache?? à voir
	}

}
