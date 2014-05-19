/**
 * public class Rene extends Thread
 * 
 * Thread representant les rennes
 *
 */
public class Rene extends Thread {

	
	private int id;//identifiant du Renne
	private static int cptId = 0;//compteur d'id
	private SalleAttenteRenes salle;//ref. sur la salle d'attente des Rennes
	private long delais;//temps de vacances des Rennes
	private boolean modeRandom;//true si le temps de vacances sera different a chaque fois
	
	
	//  CONSTRUCTEURS :
	
	/**
	 * Constructeur 1, aucun controle sur le delai des vacances du renne
	 * 
	 * @param salle : la salle ou tous les rennes vont jusqu'a ce que le 
	 * dernier renne aille reveiller le pere noel
	 * Le temps de vacances est choisi au hasard (entre 0 et 4 secondes)
	 */
	public Rene(SalleAttenteRenes salle) {
		this(salle, (long)(Math.random()*4000));
	}
	
	/**
	 * Constructeur 2 On a un controle total sur le temps des vacances du renne
	 * 
	 * @param salle : la salle ou tous les rennes vont jusqu'a ce que le dernier 
	 * renne aille reveiller le pere noel
	 * @param delais : le delai des vacances des rennes, si le delai est negatif 
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
	 * Les vacances du renne.
	 * Si on est en "mode random" le rene aura un temps de vacances differents
	 * a  chaque fois (compirs entre 0 et 4 secondes).
	 * Sinon il prendra toujours le meme temps de vacances.
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
	 * chaine retourne "Renne"+id
	 * Si on est en "mode random" la chaine de caractere retournee
	 * contien un dollard $.
	 */
	public String toString() {
		if(this.modeRandom)
			return "Renne("+this.id+")$";
		else
			return "Renne("+this.id+")";
	}
	
	/**
	 * public void run()
	 * ------------------
	 * Boucle infinie.
	 * Soit les rennes sont en vacances, soit
	 * ils rentrent et attendent la tournee de Noel. 
	 */
	public void run() {
		while(true) {
			//Le renne va partir en vacances
			this.vacances();
			
			//Puis va se mettre en attente de noel (lorsque tous les rennes seront rentres de vacances)
			this.salle.attendreNoel(this);
		}
	}
		
}
