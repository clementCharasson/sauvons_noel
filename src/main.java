public class main {

 	public static void main(String args[]) {
	
		//initialistaions :
		SalleAttenteElfes salle = new SalleAttenteElfes();
		SalleAttenteRennes salleRennes = new SalleAttenteRennes();
		
		GroupeElfe groupe = salle.getGroupe();
		PereNoel pere = new PereNoel(groupe, salleRennes);
		groupe.setPereNoel(pere);
		salleRennes.setPereNoel(pere);
		
		//Les Elfes :
		Elfe e1 = new Elfe(salle, 1.);
		Elfe e2 = new Elfe(salle, 1.);
		Elfe e3 = new Elfe(salle, 1.);
		Elfe e4 = new Elfe(salle, 1.);
		Elfe e5 = new Elfe(salle, 1.);
		Elfe e6 = new Elfe(salle, 1.);
		
		//les Rènnes :
		Renne r1 = new Renne(salleRennes,-1);//>0 = temps de vacances random
		Renne r2 = new Renne(salleRennes,-1);
		Renne r3 = new Renne(salleRennes);
		Renne r4 = new Renne(salleRennes,-1);
		Renne r5 = new Renne(salleRennes,-1);
		Renne r6 = new Renne(salleRennes,-1);
		Renne r7 = new Renne(salleRennes,-1);
		Renne r8 = new Renne(salleRennes,-1);
		Renne r9 = new Renne(salleRennes);
		
		//Start :
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

		//Join :
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
