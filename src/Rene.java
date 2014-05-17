public class Rene extends Thread {

	
	private int id;
	private static int cptId = 0;
	private SalleAttenteRenes salle;
	private long delais;
	private boolean modeRandom;
	
	
	//CONSTRUCTEURS
	
	/**
	 * Constructeur 1, aucun controle sur le delais des vacances du rène
	 * @param salle la salle où tous les renes vont jusqu'a ce que le dernier rène aille reveiller le père noel
	 */
	public Rene(SalleAttenteRenes salle) {
		this(salle, (long)(Math.random()*4000));
	}
	
	/**
	 * Constructeur 2 On a un controle total sur le temps des vacances du rène
	 * @param salle la salle où tous les renes vont jusqu'a ce que le dernier rène aille reveiller le père noel
	 * @param delais le delais des vacances des renes si le delais est négatif on est en mode random 
	 */
	public Rene(SalleAttenteRenes salle, long delais) {
		this.salle = salle;
		this.delais = delais;
		this.id = Rene.cptId++;
		this.modeRandom = delais < 0;
	}
	

	private void vacances() {
		System.out.println(TimeStamp.getTime()+"[   RENE   ]\t"+this.toString()+" demarre ses vacances (>4sec)");
		
		try {
			if (this.modeRandom)
				Thread.sleep((long)(Math.random()*4000));
			else
				Thread.sleep(this.delais);				

		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(TimeStamp.getTime()+"[   RENE   ]\t"+this.toString()+" rentre de ses vacances");
	}
	
	
	public String toString() {
		if(this.modeRandom)
			return "Rène("+this.id+")$";
		else
			return "Rène("+this.id+")";
	}
	
	public void run() {
		while(true) {
			//Le rène va partir en vacances
			this.vacances();
			
			//Puis va se mettre en attente de noel (lorsque tous les renes seront rentré de vacances)
			this.salle.attendreNoel(this);
		}
	}
		
}
