package firoz.daily.trade.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Util {

	//Standard SQL format of Date
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); 		// 	02-Jan-2016
	
	//US notation of Date 
	static SimpleDateFormat sdf_US = new SimpleDateFormat("MM/dd/yyyy"); 	//	01/02/2016

	/**
	   * This method is to convert String from 
	   * @param dateString  String
	   * @return date  		Date
	   * @throws ParseException 
	   */
	public static Date getDateFromString(String dateString) throws ParseException {
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			try {
				return sdf_US.parse(dateString);
			} catch (ParseException e1) {
				throw e1;
			}
		}
	}
	
	
	/**
	   * This method is to convert trade amount to USD based on conversion rate
	   * @param tradeRecord  	TradeData
	   * @return amountInUSD  	double
	   */
	public static double getAmountInUSD(TradeData tradeRecord) {
		return (tradeRecord.getUnits() 
				* tradeRecord.getPrice() 
				* tradeRecord.getAgreedFx());
	}
	
	/**
	   * This method is to update the date to next working date
	   * 	depends on currency of trade
	   * @param date  	java.util.Date
	   * @return date  	java.util.Date
	   */
	public static Date updateSettlementDateToNextWorkingDay(Date settlementDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(settlementDate);
		if(cal.get(Calendar.DAY_OF_WEEK)==6) {
			cal.add(Calendar.DATE, 2);
		}else if (cal.get(Calendar.DAY_OF_WEEK)==7) {
			cal.add(Calendar.DATE, 1);
		}
		settlementDate = cal.getTime();
		return settlementDate;
	}
	
	/**
	   * This method is to add entry into the hashmap
	   * 	which will later be used for calculating the aggregate sell/buy volume on a given day
	   * @param map 	Static hashmap  
	   * @param date  	java.util.Date
	   * @param amount  Amount in USD
	   * @return null 	No return value
	   */
	public static HashMap<Date, String> putOrUpdateHashmap(HashMap<Date, String> map, Date date, double amount) {
		if (map.containsKey(date)) {
			map.put(date, String.valueOf(Float.parseFloat(map.get(date)) + amount));
		} else {
			map.put(date, String.valueOf(amount));
		}
		return map;
	}
	
	/**
	   * This method is to add entry into the hashmap
	   * 	which will later be used for calculating the entity with maximum sell/buy volume
	   * @param map 	Static hashmap
	   * @param date  	java.util.Date
	   * @param amount  Amount in USD
	   * @return null 	No return value
	   */
	public static HashMap<String, String> putOrUpdateHashmap(HashMap<String, String> map, String entity, double amount) {
		if (map.containsKey(entity)) {
			map.put(entity, String.valueOf(Float.parseFloat(map.get(entity)) + amount));
		} else {
			map.put(entity, String.valueOf(amount));
		}
		return map;
	}
}
