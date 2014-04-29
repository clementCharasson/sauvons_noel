import java.util.Timer;


public class main {

	/**
	 * @param args
	 */
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
	}
	
}
