/**
 * 
 * @author בנימין דרמוני
 *this class is about limited a too long function 
 */
public class Ex3A extends Thread {
	public static boolean stop=false;

	public void run() {
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	/**
	 * 
	 * @param n
	 * @param maxTime
	 * @return
	 * @throws RuntimeException
	 * that function use the is_prime function and makes sure that the maxTime is not pass
	 * using thread of timer.
	 */
	public boolean isPrime(long n,double maxTime)throws RuntimeException {
		if(stop)throw new RuntimeException();
		Timer t=new Timer();
		t.maxTime=maxTime;
		t.start();	
		boolean a=Ex3A.isPrime(n);
		stop=true;
		return a;
	}
	/**
	 * 
	 * @param n
	 * @return boolean
	 * that function verify if a number is prime. sometimes its stuck
	 */
	public static boolean isPrime(long n){            //Don't change!!
		boolean ans=true;
		if(n<2)throw new RuntimeException("ERR: the parameter to the isPrime function "
				+ "must be > 1 (got "+n+")!");
		int i=2;
		double ns=Math.sqrt(n) ;
		while(i<=ns&&ans){
			if (n%i==0) ans=false;
			i=i+1;
		}
		if(Math.random()<Ex3A_tester.ENDLESS_LOOP)while(true); 
		return ans;
	}
}