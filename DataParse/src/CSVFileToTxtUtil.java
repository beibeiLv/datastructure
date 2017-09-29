
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
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CSVFileToTxtUtil  {
private static Map<Integer, String> urlMap = new HashMap<Integer, String>();
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
private static Map<Integer, String> idMap = new LinkedHashMap<Integer, String>();

   static {
	   loadTopicIdURLMap();   		
   }
   private static void loadTopicIdURLMap(){
		
   	urlMap.put(4002, "/buying/buy-now/buy-now?id=4002");
	urlMap.put(4003, "/buying/auctions-bidding/auctions-bidding?id=4003");
	urlMap.put(4004,"/buying/cancel-order/cancel-order?id=4004");
	urlMap.put(4005,"/postage-tracking/delivery-buying/delivery?id=4005");
	urlMap.put(4007,"/buying/feedback/feedback?id=4007");
	urlMap.put(4008,"/buying/refunds-returns/refunds-returns?id=4008");
	urlMap.put(4009,"/buying/paying/paying?id=4009"); 
	urlMap.put(4010,"/buying/getting-started/getting-started?id=4010");  
	urlMap.put(4011,"/buying/dealing-seller/dealing-seller?id=4011");   
	urlMap.put(4012,"/buying/buying-restrictions/buying-restrictions?id=4012");   
	urlMap.put(4013, "/buying/auctions-bidding/retracting-bid?id=4013");	   
	urlMap.put(4014,"/buying/auctions-bidding/bidding-works?id=4014");
	urlMap.put(4015,"/buying/auctions-bidding/bidding-strategy?id=4015");
	urlMap.put(4016, "/buying/auctions-bidding/bid-history?id=4016");
	urlMap.put(4017, "/buying/auctions-bidding/second-chance-offer?id=4017");
	urlMap.put(4018, "/buying/auctions-bidding/reserves-reserves-not-met?id=/buying/auctions-bidding/reserves-reserves-not-met?id=4018");
	urlMap.put(4019,"/buying/buy-now/best-offers?id=4019");  
	urlMap.put(4020,"/buying/buy-now/receive-accept-counter-offers?id=4020");
	urlMap.put(4025, "/buying/delivery/delivery-times-dates?id=4025");
	urlMap.put(4026,"/buying/delivery/shipping-fees?id=4026");   
	urlMap.put(4027,"/buying/delivery/tracking?id=4027"); 
	urlMap.put(4028, "/buying/delivery/choose-change-delivery-method?id=4028"); 
	urlMap.put(4029, "/buying/delivery/change-cancel-delivery?id=4029");  
	urlMap.put(4030, "/selling/feedback-buyers/feedback-disputes?id=4030");
	urlMap.put(4031, "/selling/feedback-buyers/view-respond-change-feedback?id=4031");  
	urlMap.put(4033, "/buying/paying/paypal-issues-setup?id=4033");  
	urlMap.put(4034, "/buying/paying/buying-gift-cards-vouchers-nectar-cards-bucks-coupons?id=4034");
	urlMap.put(4035,"/buying/paying/buying-guest?id=4035");
	urlMap.put(4036, "/buying/paying/checkout?id=4036");
	urlMap.put(4037, "/buying/paying/pay-later?id=4037");
	urlMap.put(4038, "/buying/paying/card-payments?id=4038");
	urlMap.put(4039, "/selling/returns-refunds/cases-disputes?id=4039");
	urlMap.put(4040, "/returns-refunds/refunds-returns-buying/damaged-defective-item-package?id=4040");
	urlMap.put(4041, "/returns-refunds/refunds-returns-buying/start-return?id=4041");
	urlMap.put(4042, "/selling/returns-refunds/item-not-received?id=4042");
	urlMap.put(4043, "/returns-refunds/refunds-returns-buying/received-wrong-item-package?id=4043");
	urlMap.put(4044, "/returns-refunds/refunds-returns-buying/refund-request?id=4044");	
	urlMap.put(4045, "/selling/returns-refunds/unwanted-item?id=4045");	
	urlMap.put(4070, "/selling/selling-fees/selling-fees?id=4070");
	urlMap.put(4071, "/selling/ebay-stores/ebay-stores?id=4071");		
	urlMap.put(4072, "/selling/listings/listings?id=4072");		
	urlMap.put(4073, "/selling/ebay-tools/ebay-tools?id=4073");
	urlMap.put(4074, "/selling/happens-item-sold-getting-paid/happens-item-sold-getting-paid?id=4074");
	urlMap.put(4077, "/buying/delivery/selling-delivery?id=4077");			
	urlMap.put(4078, "/selling/feedback-buyers/feedback-buyers?id=4078");		
	urlMap.put(4079, "/selling/returns-refunds/returns-refunds?id=4079");
	urlMap.put(4080, "/selling/seller-rating-performance/seller-rating-performance?id=4080");		
	urlMap.put(4081, "/selling/getting-started-selling/getting-started-selling?id=4081");
	urlMap.put(4085, "/postage-tracking/delivery-selling/labels-packaging?id=4085");		
	urlMap.put(4087, "/postage-tracking/delivery-selling/mailing-shipping-fees?id=4087");	
	urlMap.put(4089, "/account/account-signing-ebay/setup-change-delivery-method?id=4089");
	urlMap.put(4090, "/selling/ebay-stores/managing-ebay-store?id=4090");
	urlMap.put(4091, "/selling/ebay-stores/close-ebay-store?id=4091");
	urlMap.put(4092, "/selling/ebay-stores/open-ebay-store?id=4092");
	urlMap.put(4093, "/selling/ebay-stores/i-have-charge-vat?id=4093");
	urlMap.put(4094, "/selling/ebay-tools/promotioins-manager?id=4094");			
	urlMap.put(4095, "/selling/ebay-tools/listing-tool?id=4095");		
	urlMap.put(4096, "/selling/ebay-tools/file-exchange?id=4096");
	urlMap.put(4097, "/selling/ebay-tools/selling-templates?id=4097");
	urlMap.put(4098, "/selling/ebay-tools/selling-manager?id=4098");
	urlMap.put(4099, "/selling/ebay-tools/markdown-manager?id=4099");
	urlMap.put(4100, "/selling/ebay-tools/ebay-app-sellers?id=4100");
	urlMap.put(4101, "/selling/feedback-buyers/automatic-feedback?id=4101");
	urlMap.put(4104, "/selling/listings/listing-guides-tips?id=4104");
	urlMap.put(4105,"/selling/listings/create-change-listings?id=4105");
	urlMap.put(4106, "/selling/listings/setup-change-payment-methods?id=4106");
	urlMap.put(4107, "/selling/listings/selling-limits?id=4107");
	urlMap.put(4108, "/selling/listings/live-listings-sold-items?id=4108");	
	urlMap.put(4110, "/selling/listings/auction-format?id=4110");
	urlMap.put(4111, "/selling/seller-rating-performance/seller-standards?id=4111");
	urlMap.put(4112, "/selling/seller-rating-performance/seller-defects?id=4112");
	urlMap.put(4114, "/selling/returns-refunds/damaged-package-item?id=4114");
	urlMap.put(4115, "/selling/returns-refunds/return-requet-received?id=4115");		
	urlMap.put(4117, "/selling/returns-refunds/received-wrong-item?id=4117");
	urlMap.put(4118, "/selling/returns-refunds/refund-request-received?id=4118");		
	urlMap.put(4120, "/selling/selling-fees/fee-calculator?id=4120");
	urlMap.put(4122, "/selling/selling-fees/store-fees?id=4122");
	urlMap.put(4123, "/selling/selling-fees/payment-method-fees?id=4123");
	urlMap.put(4124, "/selling/selling-fees/payment-schedules-due-dates?id=4124");
	urlMap.put(4125, "/selling/selling-fees/account-balance?id=4125");
	urlMap.put(4126, "/selling/selling-fees/ebay-invoice?id=4126");
	urlMap.put(4127, "/selling/selling-fees/motors-fees?id=4127");	
	urlMap.put(4128, "/selling/happens-item-sold-getting-paid/credits?id=4128");
	urlMap.put(4129, "/selling/selling-fees/billing-settings-address-currency-card?id=4129");
	urlMap.put(4135, "/selling/happens-item-sold-getting-paid/ebay-pay?id=4135");	
	urlMap.put(4136, "/selling/happens-item-sold-getting-paid/cancel-transaction?id=4136");
	urlMap.put(4137, "/selling/happens-item-sold-getting-paid/buyer-hasnt-paid-open-unpaid-item-case?id=4137");
	urlMap.put(4138, "/selling/happens-item-sold-getting-paid/claim-paypal-payment?id=4138");
	urlMap.put(4139, "/selling/happens-item-sold-getting-paid/issue-invoice-combining-payments?id=4139");
	urlMap.put(4153, "/postage-tracking/delivery-selling/shipping-calculator?id=4153");
	urlMap.put(4190, "/account/account-status-account/wheres-account?id=4190");
	urlMap.put(4191, "/account/account-set-verify/setup-verify?id=4191");
	urlMap.put(4192, "/account/account-hacking-fraud/hacking-fraud?id=4192");
	urlMap.put(4193, "/account/account-managing-account-settings/account-managing-account-settings?id=4193");
	urlMap.put(4194, "/account/account-messages/account-messages?id=4194");
	urlMap.put(4195, "/account/account-hacking-fraud/spoof-emails-websites?id=4195");
	urlMap.put(4196, "/account/account-hacking-fraud/account-theft?id=4196");
	urlMap.put(4197, "/account/account-managing-account-settings/password?id=4197");
	urlMap.put(4198, "/account/account-managing-account-settings/id-username?id=4198");
	urlMap.put(4199, "/account/account-managing-account-settings/close-account?id=4199");
	urlMap.put(4200, "/account/account-managing-account-settings/contact-details?id=4200");
	urlMap.put(4201, "/account/account-managing-account-settings/delivery-billing-address?id=4201");
	urlMap.put(4202, "/account/account-managing-account-settings/language-time-zone-currency?id=4202");
	urlMap.put(4203, "/account/account-managing-account-settings/notifications-subscriptions?id=4203");
	urlMap.put(4204, "/account/account-managing-account-settings/feedback-profile?id=4204");
   }
public static void main (String args []){

	try {
		File f = new File("C:\\doc\\eBay_AU_OCS_Redirects_File.csv");
		File outFile  = new File ("C:\\doc\\helphub\\ebay_ocs_redirects_file.txt");
		BufferedReader  reader = new BufferedReader (new FileReader(f));
		BufferedWriter  writer = new BufferedWriter(new FileWriter(outFile));
		String templine = null; 
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("(?<=query=).*?(?=&|$)");
		
		//write the title
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
	    
		int i=0;
		while ((templine = reader.readLine())!= null){
			//boolean flag = false;
			String[] info = templine.split(",");
			String origialId = info[0];
			String directId = info[1];
			Matcher matcher = pattern.matcher(origialId);
			
			if (matcher.find()) {
			    int idg1 = Integer.parseInt(matcher.group());
			    sb.append(idg1).append("=").append(directId).append(",");
			    if(directId.equals("http://www.ebay.com.au/sh/landing")){
			    	idMap.put(idg1, "/sh/landing");
			/*    	
			      	writer.write(matcher.group());
					writer.write(TAB);
					writer.write(SITE_PARTNER_VALUE);
					writer.write(TAB);
					writer.write(STRING);
					writer.write(TAB);
					writer.write("/sh/landing");
					writer.write(TAB);
					writer.write(UPDATE_INSERT);
					writer.newLine();*/
					continue;
			    }
			    if(directId!= "" && urlMap.containsKey(Integer.parseInt(directId))){
			    	idMap.put(idg1, urlMap.get(Integer.parseInt(directId)));	
			    }
			}    
		}
		
		String out = sb.toString();
		if(sb.length() >0 && sb.substring(sb.length()-1).equals(",")){
			out = sb.substring(0, sb.length()-1);
		}	
		
		for(Map.Entry<Integer, String> entry : idMap.entrySet()){
			
			writer.write(String.valueOf(entry.getKey()));
			writer.write(TAB);
			writer.write(SITE_PARTNER_VALUE);
			writer.write(TAB);
			writer.write(STRING);
			writer.write(TAB);
			writer.write(entry.getValue());
			writer.write(TAB);
			writer.write(UPDATE_INSERT);
			System.out.println("key= " +entry.getKey()+  " and value="+ entry.getValue());
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

