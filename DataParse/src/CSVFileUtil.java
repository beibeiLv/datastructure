import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CSVFileUtil  {
public static void main (String args []){

	try {
		File f = new File("C:\\doc\\eBay_AU_OCS_Redirects_File.csv");
		BufferedReader  reader = new BufferedReader (new FileReader(f));
		String templine = null; 
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("(?<=query=).*?(?=&|$)");
		int i=0;
		while ((templine = reader.readLine())!= null){
			String[] info = templine.split(",");
			String origialId = info[0];
			String directId = info[1];
			Matcher matcher = pattern.matcher(origialId);
			if (matcher.find()) {
			    int idg1 = Integer.parseInt(matcher.group());
			    sb.append(idg1).append("=").append(directId).append(",");
			    i++;
			}    
		}
		
		String out = sb.toString();
		if(sb.length() >0 && sb.substring(sb.length()-1).equals(",")){
			out = sb.substring(0, sb.length()-1);
		}	
		reader.close();
		System.out.println(out);
		System.out.println("Size is " + i);

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
