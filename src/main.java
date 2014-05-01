public class main {

	/**
	 * @param args
	 */	
	
public static void main(String args[]) {
		
		SalleAttenteElfes salle = new SalleAttenteElfes();
		SalleAttenteRenes salleRenes = new SalleAttenteRenes();
		
		GroupeElfe groupe = salle.getGroupe();
		PereNoel pere = new PereNoel(groupe, salleRenes);
		groupe.setPereNoel(pere);
		salleRenes.setPereNoel(pere);
		
		Elfe e1 = new Elfe(salle, 1.);
		Elfe e2 = new Elfe(salle, 1.);
		Elfe e3 = new Elfe(salle, 1.);
		Elfe e4 = new Elfe(salle, 1.);
		Elfe e5 = new Elfe(salle, 1.);
		Elfe e6 = new Elfe(salle, 1.);
		
		
		Rene r1 = new Rene(salleRenes);
		Rene r2 = new Rene(salleRenes);
		Rene r3 = new Rene(salleRenes);
		Rene r4 = new Rene(salleRenes);
		Rene r5 = new Rene(salleRenes);
		Rene r6 = new Rene(salleRenes);
		Rene r7 = new Rene(salleRenes);
		Rene r8 = new Rene(salleRenes);
		Rene r9 = new Rene(salleRenes);
		
		
		pere.start();
		
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		r5.start();
		r6.start();
		r7.start();
		r8.start();
		r9.start();
		
		e1.start();
		e2.start();
		e3.start();
		e4.start();
		e5.start();
		e6.start();
		
		
		try {
			r1.join();
			r2.join();
			r3.join();
			r4.join();
			r5.join();
			r6.join();
			r7.join();
			r8.join();
			
			e1.join();
			e2.join();
			e3.join();
			e4.join();
			e5.join();
			e6.join();
			
			pere.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
