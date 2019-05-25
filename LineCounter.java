import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author בנימין דרמוני
 *this class count the lines number in a file using thread
 */
public class LineCounter extends Thread {
	String name;
	int line_num;
	public LineCounter (String s) {
		name=s;
		line_num=0;
	}
	public void run() {
		try {
			FileReader fr = new FileReader(name);  
			BufferedReader br = new BufferedReader(fr); 
			String str = br.readLine(); 	
			if(str!=null) {

				int i=1;
				for(; str!=null; i=i+1) { 
					str = br.readLine(); 

				}
				line_num=i-1;
			}
			br.close();
		}
		catch(Exception e) {

			e.getStackTrace();
		}


	}
}
