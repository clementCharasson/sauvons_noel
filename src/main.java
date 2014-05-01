import java.util.Timer;


public class main {

	/**
	 * @param args
	 */	
	
	public static void main(String[] args) {
		
		SalleAttenteRene sar = new SalleAttenteRene();
		PereNoel pereN = new PereNoel(sar);
		sar.setPereNoel(pereN);
		
		pereN.start();
		
		Rene r0 = new Rene(sar);
		Rene r1 = new Rene(sar);
		Rene r2 = new Rene(sar);
		Rene r3 = new Rene(sar);
		Rene r4 = new Rene(sar);
		Rene r5 = new Rene(sar);
		Rene r6 = new Rene(sar);
		Rene r7 = new Rene(sar);
		Rene r8 = new Rene(sar);
		
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
