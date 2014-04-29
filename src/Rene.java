import java.util.Random;


public class Rene extends Thread implements NoelWorker{
	
	private static int id_compt = 0;
	private int id;

	private PoolRene poolRene ;
	/**
	 * Constructeur de Rene
	 */
	public Rene(PoolRene poolRene) {
		this.poolRene = poolRene;
		this.id = this.id_compt++;
	}
	
	
	
	public void run() {
		while(true){
			this.vancances();
		}
	}
	
	public void vancances(){
		System.out.println("Rène "+id+" - je suis en vacance");
		try {
			Thread.sleep( ((long)Math.random())*5000);//attendre entre 0 et 5 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//fin des vacances :
		this.poolRene.addWorker(this);
	}
	
	public synchronized void attendre(){
		System.out.println("Rène "+id+" - attent");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}



	@Override
	public synchronized void reveiller() {
		System.out.println("Rène "+id+" je suis réveillé/ je n'attent plus");
		this.notify();
	}

}
