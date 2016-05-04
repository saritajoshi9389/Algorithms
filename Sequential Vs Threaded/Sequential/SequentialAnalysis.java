/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequential.analysis;

import com.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author sarita
 */

public class SequentialAnalysis {

    /**
     * @param args the command line arguments
     */
    // GIVEN INPUT AS -> 323.csv.gz and the file to be placed in the java package
    public static void main(String[] args) throws IOException {
        
        Scanner in = new Scanner(System.in);   // Command line input for csv file
        System.out.println("Enter Given Dataset");
        String nextLineFile = in.nextLine();
        File file = new File(nextLineFile);
        InputExtractor nextLine = new InputExtractor();
        System.out.println("In progress........!!!");
        nextLine.extract(file);
}
}
// UnGipz the given gunzip file
class InputExtractor{
    public void extract(File file) throws IOException{
        String gzip_filepath = file.getName();
        String decompressed_filepath = gzip_filepath.replace(".gz","");
        InputExtractor gZipFile = new InputExtractor();
        gZipFile.unGunzipFile(gzip_filepath, decompressed_filepath);
    }

    public void unGunzipFile(String compressedFile, String decompressedFile) {
        byte[] buffer = new byte[1024];
        try {
            FileInputStream fileIn = new FileInputStream(compressedFile);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
            FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);
            int bytes_read;
            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bytes_read);
            }
            gZIPInputStream.close();
            fileOutputStream.close();
            getCsvData(decompressedFile);
             System.out.println("Success!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    public void getCsvData(String decompressedFile) throws FileNotFoundException, IOException{
        CSVReader reader = new CSVReader(new FileReader(decompressedFile),',','"');
        String [] nextLine;
        Boolean isDataValid;
        Map<String, Float> flightPrice = new HashMap<String, Float>();
        Map<String, Double> flightTotal = new HashMap<String, Double>();
	Map<String, Double> flightAvg = new HashMap<String, Double>();
        long K=0;
        long F=0;
        float price = 0;
        while ((nextLine = reader.readNext()) != null) {
            String CRSArrTime = nextLine[40];
            String CRSDepTime  = nextLine[29];
            isDataValid = validateFields(nextLine);
            if(isDataValid){
                F++;
                if (nextLine[109].isEmpty()) price = 0;
		else
                {price = Float.parseFloat(nextLine[109]);
                    if (flightPrice.containsKey(nextLine[6])) {
			flightPrice.put(nextLine[6], flightPrice.get(nextLine[6]) + price);
			flightTotal.put(nextLine[6], flightTotal.get(nextLine[6]) + 1);} // Occurrence check
                    else {
                            flightPrice.put(nextLine[6], price); // Add 
                            flightTotal.put(nextLine[6], 1.0);}}} /// Price 
            else
                K++;
            }
        System.out.println("Value of K -> "+K);
        System.out.println("Value of F -> "+F);
        Set temp = flightPrice.keySet();
		Iterator i = temp.iterator();
		while (i.hasNext()) {
			String fc = (String) i.next();
			flightAvg.put(fc, flightPrice.get(fc) / flightTotal.get(fc));
		}
		List<Map.Entry<String, Double>> sortFreq = terms(flightAvg);
                System.out.println("Prices in increasing order-----");
                System.out.println(sortFreq);
    }
// Sorting the entries -> prices in ascending order
	static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> terms(Map<K, V> map) {
                List<Map.Entry<K, V>> entries = new ArrayList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> ob1, Map.Entry<K, V> ob2) {
				return ob1.getValue().compareTo(ob2.getValue());}});
		return entries;
        }
    
    
    public Boolean validateFields(String[] fields){
        int CRSArrTime; 
        int CRSDepTime;
         try {
                int  CRSElapsedTime = Integer.parseInt(fields[50]);
                //int ActualElapsedTime = Integer.parseInt(fields[51]);
                int cancelledFlag = Integer.parseInt(fields[47]);
                int orig_AirportID =  Integer.parseInt(fields[11]);
                int orig_AirportSeqID = Integer.parseInt(fields[12]);
                int orig_CityMarketID = Integer.parseInt(fields[13]);
                int orig_StateFips = Integer.parseInt(fields[17]);
                int orig_Wac = Integer.parseInt(fields[19]);
                int dest_AirportID = Integer.parseInt(fields[20]);
                int dest_AirportSeqID = Integer.parseInt(fields[21]);
                int dest_CityMarketID = Integer.parseInt(fields[22]);
                int dest_StateFips = Integer.parseInt(fields[26]);
                int dest_Wac = Integer.parseInt(fields[28]);
                if (fields[40].length()==4 && fields[29].length()==4) {
                CRSArrTime = Integer.parseInt(fields[40].substring(0,2))*60 + Integer.parseInt(fields[40].substring(2,4));
                CRSDepTime = Integer.parseInt(fields[29].substring(0,2))*60 + Integer.parseInt(fields[29].substring(2,4));
                }else
                    return false;
                if(fields[41].length()==4 && fields[30].length()==4){
                int ArrTime = Integer.parseInt(fields[41].substring(0,2))*60 + Integer.parseInt(fields[41].substring(2,4));
                int DepTime = Integer.parseInt(fields[30].substring(0,2))*60 + Integer.parseInt(fields[30].substring(2,4));
                }
                int timeZone = CRSArrTime - CRSDepTime - CRSElapsedTime;
                int flightStatus = Integer.parseInt(fields[47]);
                if (CRSArrTime==0 || CRSDepTime==0)
                    return false;
                else
                    if(timeZone%60!=0)
                        return false;
                else
                        if(orig_AirportID > 0 && orig_AirportSeqID > 0 && orig_CityMarketID > 0 && orig_StateFips > 0 &&
                            orig_Wac > 0 && dest_AirportID > 0 && dest_AirportSeqID > 0 && dest_CityMarketID > 0 &&
                            dest_StateFips > 0 && dest_Wac > 0)
                            return true;
                else
                            return false;
                
        } catch (NumberFormatException e){
                return false;}
    }
}


    


