
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GenerateTestFile  {
private static Map<Integer, String> urlMap = new HashMap<Integer, String>();
private static Map<String, String> idMap = new LinkedHashMap<String, String>();
private static final String TAB = "\t";
private static final String originalURl = "Orignal URL";
private static final String directlURl = "Direct URL";
//private static final String devHost = "http://www.au.lv.dev.ebay.com:9090/ocs/sr?query=";
//private static final String fpHost = "http://ocswebapp.au.stratus.qa.ebay.com/ocs/sr?query=";
private static final String qaHost = "http://ocsnext.au.paradise.qa.ebay.com/ocs/sr?query=";
   static {
	   loadTopicIdURLMap();   		
   }

	private static void loadTopicIdURLMap() {
		try {
			File f = new File(
					"C:\\doc\\helphub\\RedirectFile_generatedfromservice.csv");
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String templine = null;
			Pattern pattern = Pattern.compile("(?<=id=).*?(?=&|$)");
			while ((templine = reader.readLine()) != null) {

				String[] info = templine.split(",");
				String url = info[0];

				Matcher matcher = pattern.matcher(url);
				if (matcher.find()) {
					int idg1 = Integer.parseInt(matcher.group());
					urlMap.put(idg1, url);

				}

				}
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
public static void main (String args []){

	try {
		
		File f = new File("C:\\doc\\helphub\\eBay_AU_OCS_Redirects_File_V4.csv");
		File outFile  = new File ("C:\\doc\\helphub\\urlOutPut"+System.currentTimeMillis()+".txt");
		BufferedReader  reader = new BufferedReader (new FileReader(f));
		BufferedWriter  writer = new BufferedWriter(new FileWriter(outFile));
		String templine = null; 
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("(?<=query=).*?(?=&|$)");
		Pattern pattern2 = Pattern.compile("(?<=topicName=).*?(?=&|$)");
		//write the title
		writer.write(originalURl);
	    writer.write(TAB);
	    writer.write(directlURl);
	    writer.newLine();
	    
		int i=0;
		while ((templine = reader.readLine())!= null){
			//boolean flag = false;
			String[] info = templine.split(",");
			String origialId = info[0];
			String directId = info[1];
			Matcher matcher = pattern.matcher(origialId);
			
			if (matcher.find()) {
				String topicId = matcher.group();
			    StringBuffer sb2 = new StringBuffer();
			    sb2.append(topicId);
			    Matcher matcher2 = pattern2.matcher(origialId);
			    if(matcher2.find()){
			    	String topicName = matcher2.group();
			    	sb2.append("&topicName=").append(topicName);
			    }
			    sb.append(topicId).append("=").append(directId).append(",");
			    if(directId!= "" && urlMap.containsKey(Integer.parseInt(directId))){
			    	idMap.put(sb2.toString(), urlMap.get(Integer.parseInt(directId)));	
			    }
			}    
		}
		
		String out = sb.toString();
		if(sb.length() >0 && sb.substring(sb.length()-1).equals(",")){
			out = sb.substring(0, sb.length()-1);
		}	
		
		for(Map.Entry<String, String> entry : idMap.entrySet()){
			
			writer.write(qaHost+entry.getKey());
			writer.write(TAB);
		
			writer.write(entry.getValue());
			
			writer.newLine();
			i++;
		}
		reader.close();
		writer.close();
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

