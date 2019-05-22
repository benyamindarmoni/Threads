
public class Ex3A extends Thread {
	public static boolean stop=false;
	private long number;

	public void run() {
		try {
			this.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	public boolean isPrime(long n,double maxTime)throws RuntimeException {
		if(stop)throw new RuntimeException();
		number=n;
		Timer t=new Timer();
		t.maxTime=maxTime;
		t.start();	
		boolean a=Ex3A.isPrime(number);
		stop=true;
		return a;
	}
	public static boolean isPrime(long n){
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