import java.util.Random;


public class Rene extends Thread implements NoelWorker{
	
	private static int id_compt = 0;
	public int id;

	private SalleAttenteRene sar;
	/**
	 * Constructeur de Rene
	 */
	public Rene(SalleAttenteRene sar) {
		this.sar = sar;
		this.id = this.id_compt++;
	}
	
	
	
	public void run() {
		while(true){
			this.vancances();
		}
	}
	
	public void vancances(){
		long duree = (long) ( Math.random()*4000 );//attendre entre 0 et 4 secondes
		System.out.println("Rène "+id+" - je suis en vacance  durée ="+duree+" ms");
		try {
			Thread.sleep(duree);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Rène "+id+" - fin des vacances");
		//fin des vacances le rène va ce mettre en à l'étable pour attendre le réveille du père Noël:
		sar.attendreNoel(this);
	}
	


}
