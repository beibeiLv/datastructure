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
					topicId_url_Map.put(idg1, url);

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

		try {

			File f = new File(
					"C:\\doc\\helphub\\eBay_AU_OCS_Redirects_File_V4.csv");
			File outFile = new File("C:\\doc\\helphub\\ebay_ocs_redirects_file"
					+ System.currentTimeMillis() + ".txt");
			BufferedReader reader = new BufferedReader(new FileReader(f));
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			String templine = null;
			StringBuffer sb = new StringBuffer();
			Pattern pattern = Pattern.compile("(?<=query=).*?(?=&|$)");
			Pattern pattern2 = Pattern.compile("(?<=topicName=).*?(?=&|$)");
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

			int i = 0;
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
									topicName = XssCheckUtil.removeIllegalCharacters(URLDecoder.decode(URLDecoder.decode(topicName, "UTF-8"),"UTF-8"));
								} catch (Exception e) {
								}
								topicName = topicName.replaceAll("\"", "").replaceAll(SPACE_CHAR, UNDERSCORE_CHAR);
							}
							sb2.append(UNDERSCORE_CHAR).append(topicName);
						}

						sb.append(sb2).append("=").append(directId).append(",");
						if (directId != "" && topicId_url_Map.containsKey(Integer
										.parseInt(directId))) {
							directMap.put(sb2.toString(),
									topicId_url_Map.get(Integer.parseInt(directId)));
						}
					}
				}
			}

			String out = sb.toString();
			if (sb.length() > 0 && sb.substring(sb.length() - 1).equals(",")) {
				out = sb.substring(0, sb.length() - 1);
			}

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
				System.out.println("key= " + entry.getKey() + " and value="
						+ entry.getValue());
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

	private static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
}
