
public class PereNoel extends Thread{
	private SalleAttenteRene salleRene;

	PereNoel(SalleAttenteRene salleRene /*TODO salle d'attente des elfes*/){
		this.salleRene = salleRene;
	}
	
	
	public void  run(){
		while(true){
			this.dormir();
			System.out.println("Pere noel réveillé - va faire un traitement");
			this.traitement();
			System.out.println("Pere noel réveillé - fin faire un traitement");
		}
	}
	
	public synchronized void dormir(){
		System.out.println("Pere Noël - dort");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void reveille(){
		System.out.println("Pere Noël - réveillé");
		this.notify();//réveiller le père Noël
		System.out.println("Pere Noël - réveillé 2");
	}
	
	private synchronized void traitement(){
		//regarder la salle d'attente des Rènes :
		if(salleRene.getNbRene() == 9){
			this.tourne();
		}
		/*
		//regarder la salle d'attente des Efles :
		if(???){
			
			//On resoud les problemes des elfes
			this.resoudreProbleme();
		}
		*/
		System.out.println("traitement fini");
	}
	
	
	//traitement pour les Rènes
	private synchronized void tourne(){//la tournée du pere Noel
		System.out.println("Père noël - C'est la tournée de Noël");
		try {
			Thread.sleep(3000);//tournée
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Père noël - Fin de la tournée du Père Noël");
		salleRene.libererRenes();//libérer les Rènes à la fin de Noël
		System.out.println("Père noël - Fin de la tournée du Père Noël 2");
	}
	
	
	//traitement pour les Elfes
	private void resoudreProbleme(){
		System.out.println("le Père noël - résoud des problènes");
		
	}

}
