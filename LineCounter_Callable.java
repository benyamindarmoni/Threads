import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class LineCounter_Callable implements Callable<Integer> {
	String name;
	int line_num;
	public LineCounter_Callable (String s) {
		name=s;
		line_num=0;
	}
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
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
		//System.out.println(line_num);
		return line_num;


	}

}


