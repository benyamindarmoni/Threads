public class Ex3A_tester {                 //Don't change!!
	/** This class represents a basic implementation for Ex3testing file. */
	public static double ENDLESS_LOOP=0.4;
	public static void main(String[]args){
		Ex3A ex3a=new Ex3A();
		long n=33333331;
		boolean ans=ex3a.isPrime(n,0.01);
		System.out.println("n="+n+" isPrime "+ans);

	}
}