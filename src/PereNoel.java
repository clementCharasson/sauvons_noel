
public class PereNoel extends Thread {
	
	private GroupeElfe groupe;
	private SalleAttenteRenes salleRenes;
	
	public PereNoel(GroupeElfe groupe, SalleAttenteRenes salleRenes) {
		this.groupe = groupe;
		this.salleRenes = salleRenes;
	}
	
	
	public synchronized void dormir() {
		System.out.println(TimeStamp.getTime()+"[PERE NOEL ]\t"+"va dormir");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(TimeStamp.getTime()+"[PERE NOEL ]\t"+"se reveille");
	}
	
	public synchronized void reveille(){
		notify();
	}
	
	private void traitement() {
		while(this.salleRenes.auComplet() || this.groupe.estPlein()) {
			if(this.salleRenes.auComplet() && this.groupe.estPlein()) {
				System.out.println(TimeStamp.getTime()+"[PERE NOEL ]\t"+"est face a tous les renes et tous les elfes");//
			}
			//On va dans un premier temps regarder si les renes sont au complet
			if(this.salleRenes.auComplet()) {
				System.out.println(TimeStamp.getTime()+"[PERE NOEL ]\t"+"====demarre la TOURNÉ (5 sec) ========");
				this.salleRenes.tournee();
				System.out.println(TimeStamp.getTime()+"[PERE NOEL ]\t"+"====FIN la tourné (5 sec) ========");
			}
			
			//Puis ensuite les elfes
			if(this.groupe.estPlein()) {
				System.out.println(TimeStamp.getTime()+"[PERE NOEL ]\t"+"====va RESOUDRE les problemes====");
				this.groupe.resoudreProbleme();
				System.out.println(TimeStamp.getTime()+"[PERE NOEL ]\t"+"====a RÉSOLUT les problemes====");
			}
		}
	}
	
	public void run() {
		while(true) {
			this.dormir();
			
			this.traitement();
		}
	}
}
