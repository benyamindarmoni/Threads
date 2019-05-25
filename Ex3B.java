
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author בנימין דרמוני
 *class goal: compare thread system, thread pool system and one process system.
 */
public class Ex3B {
	public static void main(String[] args) {
		int num = 1000; 
		System.out.println("Count lines Threads : ");
		countLinesThread(num);

		System.out.println("**********************");
		System.out.println("Count lines One Process : ");
		countLinesOneProcess(num);

		System.out.println("**********************");
		System.out.println("Count lines ThreadPool : ");
		countLinesThreadPool(num);
		/**/
	}
	/**
	 * 
	 * @param n amount of files to create
	 * @return an array of files name
	 * this function create files, and write inside random of lines "Hello World"  
	 */
	public static String[] createFiles(int n) {
		Random r = new Random(123); 
		String files[]=new String [n];
		//int sum=0;
		try {
			for(int i=0;i<n;i++) {
				files[i]="File_"+(i+1);
				File file=new File(files[i]);
				file.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(files[i])); 
				int numLines = r.nextInt(1000);
				//sum+=numLines;
				for(int j=0;j<numLines;j++) {
					bw.write("Hello World");
					bw.newLine();
				}
				bw.close();
			}//System.out.println("sum of lines expected : "+sum);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return files;
	}

	/**
	 * 
	 * @param fileNames:  an array of files names
	 * that function delete all the files that mention in the array
	 */
	public static void deleteFiles(String[] fileNames) {
		try {
			for(int i=0;i<fileNames.length;i++) {
				File file = new File(fileNames[i]);
				if(file.delete()){
					//System.out.println("File deleted from Project root directory");
				}
				else System.out.println("File doesn't exist in the project root directory");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param numFiles
	 * that function use the up function .
	 * first create the files, and then use the line counter thread to 
	 * count sum of lines inside the files .
	 * then its delete all files
	 * 
	 */
	public static void countLinesThread (int numFiles)   {
		try {
			int sum=0;
			String files[]=createFiles(numFiles);	
			long begin=System.currentTimeMillis();
			LineCounter counters[]=new LineCounter[numFiles];
			for (int i = 0; i < numFiles; i++) {
				counters[i]=new LineCounter(files[i]);
				counters[i].start();
			}
			while(true) {
				int sum2=0;
				for(int i=0;i<counters.length;i++) {
					if(!counters[i].isAlive())sum2++;
				}
				if(sum2==counters.length)break;
			}
			for(int i=0;i<counters.length;i++) {
				sum+=counters[i].line_num;
			}
			long run_time=System.currentTimeMillis()-begin;
			System.out.println("sum of lines is: "+sum);
			System.out.println("run time is: "+run_time+" in Millis");
			deleteFiles(files);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	/**
	 * 
	 * @param num
	 * that function use the up function .
	 * first create the files, and then use the line counter callable  with a pool
	 * thread to count sum of lines inside the files .
	 * then its delete all files
	 * 
	 */
	public static void countLinesThreadPool(int num) {
		try {
			int sum=0;
			String files[]=Ex3B.createFiles(num);
			long begin=System.currentTimeMillis();
			ExecutorService pool=Executors.newFixedThreadPool(num/8);
			for(int i=0;i<num;i++) {
				LineCounter_Callable a = new LineCounter_Callable((files[i]));
				Future<Integer> answer=pool.submit(a);
				sum+=answer.get();
			}
			long run_time=System.currentTimeMillis()-begin;
			System.out.println("sum of lines is: "+sum);
			System.out.println("run time is: "+run_time+" in Millis");
			Ex3B.deleteFiles(files);
			pool.shutdown();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param num
	 * that function use the up function .
	 * first create the files, and then  count sum of lines inside the files without thread system! .
	 * in One Process .then its delete all files
	 * 
	 */
	public static void countLinesOneProcess(int num) {
		long run_time=0;
		try {
			int sum =0;
			String files[]=Ex3B.createFiles(num);
			try {
				long begin=System.currentTimeMillis();
				for(int i=0;i<num;i++) {
					FileReader fr = new FileReader(files[i]);  
					BufferedReader br = new BufferedReader(fr); 
					String str = br.readLine(); 	
					if(str!=null) {

						int j=1;
						for(; str!=null; j++) { 
							str = br.readLine(); 

						}
						sum=sum+j-1;
					}
					br.close();
				} run_time=System.currentTimeMillis()-begin;
			}
			catch(Exception e) {

				e.getStackTrace();
			}

			//code

			System.out.println("sum of lines is: "+sum);
			System.out.println("run time is: "+run_time+" in Millis");

			Ex3B.deleteFiles(files);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
