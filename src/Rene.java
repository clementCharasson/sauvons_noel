/**
 * public class Rene extends Thread
 * 
 * Thread représentant les rènes
 *
 */
public class Rene extends Thread {

	
	private int id;//identifiant du Rène
	private static int cptId = 0;//compteur d'id
	private SalleAttenteRenes salle;//ref. sur la salle d'attente des Rènes
	private long delais;//temps de vacances des Rènes
	private boolean modeRandom;//si le true le temps de vacances sera différent à chaque fois
	
	
	//  CONSTRUCTEURS :
	
	/**
	 * Constructeur 1, aucun controle sur le delais des vacances du rène
	 * 
	 * @param salle : la salle où tous les rènes vont jusqu'a ce que le 
	 * dernier rène aille reveiller le père noel
	 * Le temps de vacances est choisit au hazard (entre 0 et 4 secondes)
	 */
	public Rene(SalleAttenteRenes salle) {
		this(salle, (long)(Math.random()*4000));
	}
	
	/**
	 * Constructeur 2 On a un controle total sur le temps des vacances du rène
	 * 
	 * @param salle : la salle où tous les renes vont jusqu'a ce que le dernier 
	 * rène aille reveiller le père noel
	 * @param delais : le delais des vacances des renes, si le delais est négatif 
	 * on est en mode random 
	 */
	public Rene(SalleAttenteRenes salle, long delais) {
		this.salle = salle;
		this.delais = delais;
		this.id = Rene.cptId++;
		this.modeRandom = delais < 0;
	}
	
	/**
	 * private void vacances()
	 * ----------------------------
	 * Les vacances du rene.
	 * Si on est en "mode random" le rene aura un temps de vacances différents
	 * à chaque fois (compirs entre 0 et 4 secondes).
	 * Sinon il prendra toujours le même temps de vacances à chaques fois.
	 */
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
	
	/**
	 * public String toString()
	 * --------------------------
	 * chaine retourné "Rène"+id
	 * Si on est en "mode random" la chaine de caractère retourné
	 * contien un dollard $.
	 */
	public String toString() {
		if(this.modeRandom)
			return "Rène("+this.id+")$";
		else
			return "Rène("+this.id+")";
	}
	
	/**
	 * public void run()
	 * ------------------
	 * Boucle infi.
	 * Les Rènes sont soit en vacances, soit
	 * ils rentrent et attendent la tournée de Noel. 
	 */
	public void run() {
		while(true) {
			//Le rène va partir en vacances
			this.vacances();
			
			//Puis va se mettre en attente de noel (lorsque tous les renes seront rentré de vacances)
			this.salle.attendreNoel(this);
		}
	}
		
}
