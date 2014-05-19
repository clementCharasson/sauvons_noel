/**
 * public class TimeStamp
 * 
 * classe pour afficher un petit time stamp dans les messages
 * @author clement
 */
public class TimeStamp {
	
	private final long start;
	public static final TimeStamp tstamp = new TimeStamp();
	
	public TimeStamp() {
		this.start = System.currentTimeMillis()/ 1000L;
	}
	
	/**
	 * Pour avoir la difference entre le temps de depart et le temps
	 * courant.
	 * @return
	 */
	public static long getTime(){
		return (System.currentTimeMillis()/ 1000L) - tstamp.start;
	}
}
