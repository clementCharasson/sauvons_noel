
public class Rene extends Thread implements NoelWorker{

	/**
	 * Constructeur de Rene
	 */
	public Rene() {
		
	}
	
	
	
	public void run() {
		
	}
	
	public void vancances(){
		
	}
	
	public synchronized void attendre(){
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void reveiller() {
		this.notify();
		
	}

}
