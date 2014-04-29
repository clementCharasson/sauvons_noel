
public class PereNoel extends Thread{
	
	Pool pool ;

	PereNoel(Pool pool){
		this.pool = pool;
	}
	
	
	public void  run(){
		while(true){
			this.dormir();
			System.out.print("Je me suis réveillé \n");
			//this.tourne();
			
		}
	}
	
	public synchronized void dormir(){
		System.out.print("Je dors !\n");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void réveille(NoelWorker nw){
		this.notify();
	}
	
	public void tourne(){//la tournée du pere Noel
		//libère rène du pool
	}

}
