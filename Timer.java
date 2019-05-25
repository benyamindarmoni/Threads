/**
 * 
 * @author בנימין דרמוני
 *This class using as a timer, verify that the dead line not pass
 */
public class Timer extends Thread {
	double maxTime;
	public void run() throws RuntimeException{
		double begin=System.currentTimeMillis();
		while(!Ex3A.stop&&((System.currentTimeMillis()-begin)<(maxTime*1000)));
		if(!Ex3A.stop) {
			Ex3A.stop=true;
			Ex3A breaker=new Ex3A();
			breaker.start();
			breaker.isPrime(0,0);
		}
	}
}