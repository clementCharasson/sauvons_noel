import java.util.Timer;


public class main {

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		Pool p = new Pool();
		PereNoel pn = new PereNoel(p);
		p.setPereNoel(pn);
		
		pn.start();
		
		//le temps de créer le thread :
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 
		p.reveil();

		try {
			pn.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	
		System.out.println("fin du programme");
	}*/
	
	
	public static void main(String[] args) {
		/*
		PoolRene poolR = new PoolRene();
		
		PereNoel pereN = new PereNoel(poolR);
		*/
		
		for(int i = 0; i < 10; i++) {
			boolean prob = Probleme.rencontrerProbleme(1);
			
			if(prob)
				System.out.println("VRAI");
			else
				System.out.println("FAUX");
		}
		
		PoolElfes poolE = new PoolElfes();
		
		PereNoel pereN = new PereNoel(poolE);
		poolE.setPereNoel(pereN);
		
		Elfe e1 = new Elfe(poolE, 2000, 1);
		e1.setNom("e1");
		Elfe e2 = new Elfe(poolE, 2000, 1);
		e2.setNom("e2");
		Elfe e3 = new Elfe(poolE, 2000, 1);
		e3.setNom("e3");
		Elfe e4 = new Elfe(poolE, 2000, 1);
		e4.setNom("e4");
		
		
		pereN.start();
		e1.start();
		e2.start();
		e3.start();
		e4.start();
	}
	
}
