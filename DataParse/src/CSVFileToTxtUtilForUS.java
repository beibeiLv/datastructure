
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


public class CSVFileToTxtUtilForUS  {
private static Map<String, String> directMap = new LinkedHashMap<String, String>();
private static final String TAB = "\t";
private static final String COMMA = ",";
private static final String Key = "key";
private static final String SITE = "site";
private static final String PARTENR = "partner";
private static final String TYPE = "type";

private static final String VALUE = "value";
private static final String MODE = "mode";
private static final String STRING = "string";
private static final String SITE_PARTNER_VALUE = "US,eBay";
private static final String UPDATE_INSERT = "update.insert";
//private static Map<Integer, String> idMap = new LinkedHashMap<Integer, String>();
private static Map<Integer, String> topicId_url_Map = new HashMap<Integer, String>();
   static {
	   loadTopicIdURLMap();
	   loadDirectMap();  		
   }
   private static void loadTopicIdURLMap() {
		try {
			File f = new File("C:\\doc\\helphub\\Book2.csv");
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String templine = null;
			Pattern pattern = Pattern.compile("(?<=id=).*?(?=&|$)");
			while ((templine = reader.readLine()) != null) {

				String[] info = templine.split(",");
				String url = info[0];

				Matcher matcher = pattern.matcher(url);
				if (matcher.find()) {
					int idg1 = Integer.parseInt(matcher.group().trim());
					topicId_url_Map.put(idg1, url);

				}
			}
			reader.close();
			System.out.println(topicId_url_Map.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
public static void main (String args []){
	exportFCFile();
}
private static void loadDirectMap() {
try {
	File f = new File("C:\\doc\\helphub\\Book11.csv");
	File outFile  = new File ("C:\\doc\\helphub\\queryId_HHURL_mapping.csv");
	BufferedReader  reader = new BufferedReader (new FileReader(f));
	BufferedWriter  writer = new BufferedWriter(new FileWriter(outFile));
	String templine = null; 
//	StringBuffer sb = new StringBuffer();
//	Pattern pattern = Pattern.compile("(?<=query=).*?(?=&|$)");

    
	int i=0;
	while ((templine = reader.readLine())!= null){
		//boolean flag = false;
		String[] info = templine.split(",");
		String origialId = info[0];
		String directId = info[3];
//		Matcher matcher = pattern.matcher(origialId);
		if (null!= directId && !"".equals(directId) && isNumeric(origialId) &&isNumeric(directId)){
					/*if (!isNumeric(directId)) {
						directMap.put(Integer.parseInt(origialId), "(blank)");
					} else {
						directMap.put(
								Integer.parseInt(origialId),
								topicId_url_Map.get(Integer.parseInt(directId)) == null ? directId : topicId_url_Map
										.get(Integer.parseInt(directId)));
					}*/
			if(	topicId_url_Map.get(Integer.parseInt(directId)) != null ){
				directMap.put(
						origialId,
						topicId_url_Map
								.get(Integer.parseInt(directId)));
			}
			
		}/*else if (isNumeric(origialId) && !isNumeric(directId)){
			idMap.put(Integer.parseInt(origialId),"BLANK");
		}*/
	}
	
	
	
	for(Map.Entry<String, String> entry : directMap.entrySet()){
		
		writer.write(entry.getKey());
		writer.write(",");
	/*	if(null == entry.getValue() || "".equals(entry.getValue())){
			writer.write("BLANK");
		}else{
			writer.write(entry.getValue());
		}*/
		writer.write(entry.getValue());
		writer.newLine();
		i++;
	}
	reader.close();
	writer.close();

	System.out.println("Size is " + i);

	} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}
private static boolean isNumeric(String str) {

	Pattern pattern = Pattern.compile("[0-9]*");
	Matcher isNum = pattern.matcher(str);
	if (!isNum.matches()) {
		return false;
	}
	return true;
}
public static void exportFCFile(){
	File outFile = new File("C:\\doc\\helphub\\ebay_ocs_redirects_file_us_" + System.currentTimeMillis() + ".txt");
	int i = 0;
	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		writeTiTle(writer);
		for (Map.Entry<String, String> entry : directMap.entrySet()) {
			writer.write(entry.getKey());
			writer.write(TAB);
			writer.write(SITE_PARTNER_VALUE);
			writer.write(TAB);
			writer.write(STRING);
			writer.write(TAB);
			writer.write(entry.getValue());
			writer.write(TAB);
			writer.write(UPDATE_INSERT);
			System.out.println("key= " + entry.getKey() + " and value=" + entry.getValue());
			writer.newLine();
			i++;
		}
		writer.close();
		System.out.println("Size is " + i);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
}

private static void writeTiTle(BufferedWriter writer) throws IOException {
	// write the title
	writer.write(Key);
	writer.write(TAB);
	writer.write(SITE);
	writer.write(COMMA);
	writer.write(PARTENR);
	writer.write(TAB);
	writer.write(TYPE);
	writer.write(TAB);
	writer.write(VALUE);
	writer.write(TAB);
	writer.write(MODE);
	writer.newLine();
}
}

