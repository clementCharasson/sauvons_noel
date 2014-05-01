
public class SalleAttenteRene {
	private int nbRene;//nombres de Rène en attentes
	private PereNoel pereNoel;
	private static boolean Noel;//pour savoir si c'est Noël (variable partagé)
	
	public SalleAttenteRene() {
		this.nbRene = 0;
		this.Noel = false;
	}
	

	public synchronized void attendreNoel(Rene r){//le rène en paramètre est juste
		this.nbRene ++;//on incrémente le nombre de Rènes en attentes
		System.out.println("Rene "+r.id+" - attent Noel  nbRene ="+this.nbRene);
		if(this.nbRene == 9){//si nous somme le 9 ème Rène à rentrer nous réveillons le père Noël
			this.Noel = true;
			System.out.println("Rene "+r.id+" va réveiller le pere noel isinterrupt="+pereNoel.isInterrupted()+"  isalive="+pereNoel.isAlive());
			pereNoel.reveille();//réveiller le père Noel qui va libérer tout les Rènes à la fin de ça tounée
			System.out.println("Rene "+r.id+" a réveiller le père noel ="+pereNoel.isInterrupted()+"  isalive="+pereNoel.isAlive());
			/*
			while(this.Noel){//attente active
				//VERSION ATTENTE PASSIVE
				System.out.println("Rene "+r.id+" - attente passive");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Rene "+r.id+" - fin attente passive");
				*
				//VERSION ATTENTE ACTIVE
				System.out.println("Rene "+r.id+" - attente active");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			System.out.println("Rene "+r.id+" - fin attente active");
			*/
		}//else{//sinon on attend
			try {
				System.out.println("Rene "+r.id+" - attente pour de vrai");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
		
	}
	
	/**
	 * méthode que va appeler le père Noël pour libérer tout les
	 * Rènes en attente
	 */
	public synchronized void libererRenes(){
		System.out.println("---avant libération Rènes---");
		this.Noel = false;//fin de Noël
		this.notifyAll();//réveille des Rènes
		this.nbRene = 0;
		System.out.println("---Rènes libres----");
	}

	public PereNoel getPereNoel() {
		return pereNoel;
	}


	public void setPereNoel(PereNoel pereNoel) {
		this.pereNoel = pereNoel;
	}


	public int getNbRene() {
		return nbRene;
	}
	
	
}
