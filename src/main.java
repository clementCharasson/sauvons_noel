import java.util.Timer;


public class main {

	/**
	 * @param args
	 */	
	
	public static void main(String[] args) {
		
		PoolRene poolR = new PoolRene();
		
		
		for(int i = 0; i < 10; i++) {
			boolean prob = Probleme.rencontrerProbleme(1);
			
			if(prob)
				System.out.println("VRAI");
			else
				System.out.println("FAUX");
		}
		
		PoolElfes poolE = new PoolElfes();
		
		PereNoel pereN = new PereNoel(poolR, poolE);
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
		
		poolR.setPereNoel(pereN);
		
		Rene r0 = new Rene(poolR);
		Rene r1 = new Rene(poolR);
		Rene r2 = new Rene(poolR);
		Rene r3 = new Rene(poolR);
		Rene r4 = new Rene(poolR);
		Rene r5 = new Rene(poolR);
		Rene r6 = new Rene(poolR);
		Rene r7 = new Rene(poolR);
		Rene r8 = new Rene(poolR);
		
		r0.start();
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		r5.start();
		r6.start();
		r7.start();
		r8.start();
		
		
		
		try {	
			pereN.join();
			r0.join();
			r1.join();
			r2.join();
			r3.join();
			r4.join();
			r5.join();
			r6.join();
			r7.join();
			r8.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
