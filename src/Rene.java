import java.util.Random;


public class Rene extends Thread implements NoelWorker{

	/**
	 * Constructeur de Rene
	 */
	public Rene() {
		
	}
	
	
	
	public void run() {
		
	}
	
	public void vancances(){
		System.out.println("Rène - je suis en vacance");
		try {
			Thread.sleep( ((long)Math.random())*5000);//attendre entre 0 et 5 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
