import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpHubCSVFileUtil {
	private static Map<Integer, String> topicId_url_Map = new HashMap<Integer, String>();
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
	private static final String SITE_PARTNER_VALUE = "Global,eBay";
	private static final String UPDATE_INSERT = "update.insert";

	private final static String SPACE_CHAR = " ";
	private final static String UNDERSCORE_CHAR = "_";
	
	private static final String originalURl = "Orignal URL";
	private static final String directlURl = "Direct URL";
	//private static final String devHost = "http://www.au.lv.dev.ebay.com:9090/ocs/sr?query=";
	//private static final String fpHost = "http://ocswebapp.au.stratus.qa.ebay.com/ocs/sr?query=";
	private static final String qaHost = "http://ocsnext.au.paradise.qa.ebay.com/ocs/sr?query=";
	
	static {
		loadTopicIdURLMap();
		loadDirectMap();
	}

	private static void loadTopicIdURLMap() {
		try {
			File f = new File("C:\\doc\\helphub\\RedirectFile_generatedfromservice_V3.csv");
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
	public static void exportFCFile(){
		File outFile = new File("C:\\doc\\helphub\\ebay_ocs_redirects_file_v3_" + System.currentTimeMillis() + ".txt");
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
	
	public static void exportTestFile(){
		File outFile = new File("C:\\doc\\helphub\\urlOutPut" + System.currentTimeMillis() + ".txt");
		File f = new File("C:\\doc\\helphub\\RedirectFile_generatedfromservice_V3.csv");
	    Map<String, String>  map = new LinkedHashMap<String, String>();
		int i = 0;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			writeTestFileTiTle(writer);
			BufferedReader  reader = new BufferedReader (new FileReader(f));
			String templine = null; 
			Pattern pattern = Pattern.compile("(?<=query=).*?(?=&|$)");
			Pattern pattern2 = Pattern.compile("(?<=topicName=).*?(?=&|$)");
	 
			while ((templine = reader.readLine())!= null){
				String[] info = templine.split(",");
				String origialId = info[0];
				String directId = info[1];
				Matcher matcher = pattern.matcher(origialId);
				
				if (matcher.find()&& isNumeric(directId)) {
					String topicId = matcher.group();
					StringBuffer sb1 = new StringBuffer();
				    StringBuffer sb2 = new StringBuffer();
				    sb1.append(topicId);
				    sb2.append(topicId);
				    Matcher matcher2 = pattern2.matcher(origialId);
				    if(matcher2.find()){
				    	String topicName = matcher2.group();
				    	if (topicName != null) {
				    		String topicNameStr ="";
							try {
								 topicNameStr = XssCheckUtil.removeIllegalCharacters(URLDecoder.decode(URLDecoder.decode(topicName, "UTF-8"), "UTF-8"));
							} catch (Exception e) {
								
							}
							topicNameStr = topicNameStr.replaceAll("\"", "").replaceAll(SPACE_CHAR, UNDERSCORE_CHAR);
						
						sb2.append(UNDERSCORE_CHAR).append(topicNameStr);
						sb1.append("&topicName=").append(topicName);
				    }
				    }
				    if(directId!= "" && directMap.containsKey(sb2.toString())){
				    	map.put(sb1.toString(), directMap.get(sb2.toString()));	
				    }
				}    
			}
			
			for(Map.Entry<String, String> entry : map.entrySet()){
				
				writer.write(qaHost+entry.getKey());
				writer.write(TAB);
				writer.write(entry.getValue());
				writer.newLine();
				i++;
			}
			writer.close();
			reader.close();
			System.out.println("Size is " + i);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	private static void writeTestFileTiTle(BufferedWriter writer) throws IOException {
		//write the title
		writer.write(originalURl);
	    writer.write(TAB);
	    writer.write(directlURl);
	    writer.newLine();
		
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
	

	private static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	private static void loadDirectMap() {
		try {
			File f = new File("C:\\doc\\helphub\\eBay_AU_OCS_Redirects_File_V4_V2.csv");
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String templine = null;
			StringBuffer sb = new StringBuffer();
			Pattern pattern = Pattern.compile("(?<=query=).*?(?=&|$)");
			Pattern pattern2 = Pattern.compile("(?<=topicName=).*?(?=&|$)");
			while ((templine = reader.readLine()) != null) {
				String[] info = templine.split(",");
				String origialId = info[0];
				String directId = info[1];
				Matcher matcher = pattern.matcher(origialId);

				if (matcher.find() && isNumeric(directId)) {
					if (isNumeric(directId)) {
						String topicId = matcher.group();

						StringBuffer sb2 = new StringBuffer();
						sb2.append(topicId);
						Matcher matcher2 = pattern2.matcher(origialId);
						if (matcher2.find()) {
							String topicName = matcher2.group();
							if (topicName != null) {
								try {
									topicName = XssCheckUtil.removeIllegalCharacters(URLDecoder.decode(URLDecoder.decode(topicName, "UTF-8"), "UTF-8"));
								} catch (Exception e) {
									
								}
								topicName = topicName.replaceAll("\"", "").replaceAll(SPACE_CHAR, UNDERSCORE_CHAR);
							}
							sb2.append(UNDERSCORE_CHAR).append(topicName);
						}

						sb.append(sb2).append("=").append(directId).append(",");
						if (directId != "" && topicId_url_Map.containsKey(Integer.parseInt(directId))) {
							directMap.put(sb2.toString(), topicId_url_Map.get(Integer.parseInt(directId)));
						}
					}
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
	
	public static void main(String args[]) {
		exportFCFile();
		//exportTestFile();
	}
}