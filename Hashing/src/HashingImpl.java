import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HashingImpl {
	public static final int M = 701;
	public static void main(String args[]){
		
		String path = "alice_in_wonderland.txt";
		Scanner scanner = null;
		String text = null;
		try {
			scanner = new Scanner(new File(path));
			text = scanner.useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
		}finally{
			scanner.close();
		}
		StringSplit sp = new StringSplit();
		String[] splitStr = sp.split(text);
		System.out.println(splitStr.length);
		
		Hashing h = new Hashing();
		for(int i=0; i<splitStr.length; i++){
			if(splitStr[i]!=null && !splitStr[i].isEmpty()){
				h.increase(splitStr[i]);
			}
		}
		
		h.printKeyVal();
		
		// Write count in a file
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("output.txt");
			int c = 0;
			for(String k: h.listAllKeys()){
				pw.println(k + " -> " + h.find(k));
				c++;
			}
			System.out.println("TotalKeys = "+c);
		} catch (FileNotFoundException e) {
			System.out.println("Error writing to file");
			e.printStackTrace();
		}finally{
			pw.close();
		}
		
		
	}
}
