package firoz.daily.trade.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;


public class DailyTradeReport 
{
	static Logger logger = Logger.getLogger(DailyTradeReport.class);

	HashMap<Date, String> settledIncoming = new HashMap<>(); 	// S – Sell – incoming
	HashMap<Date, String> settledOutgoing = new HashMap<>(); 	// B – Buy – outgoing
	
	HashMap<String, String> settledIncomingTrans = new HashMap<>(); // S – Sell – incoming
	HashMap<String, String> settledOutgoingTrans = new HashMap<>(); // B – Buy – outgoing
	
	/**
	   * This method should read input records and do further operation
	   */
	public void processInputData(List<TradeData> dataList) {
		logger.info("Processing Input Data.............");
		
		for(TradeData data : dataList) {
			logger.debug(data);
			
			double amountUSD = Util.getAmountInUSD(data);
			
			if(data.getCurrency().equalsIgnoreCase("AED") || data.getCurrency().equalsIgnoreCase("SAR")) {
				data.setSettlementDate( Util.updateSettlementDateToNextWorkingDay(data.getSettlementDate()) );
				logger.debug("After updating SettlementDate - \n"+ data);
			}
			
			if (data.getAction().equalsIgnoreCase("B")) {
				settledOutgoing = Util.putOrUpdateHashmap(settledOutgoing, data.getSettlementDate(), amountUSD);
				settledOutgoingTrans = Util.putOrUpdateHashmap(settledOutgoingTrans, data.getEntity(), amountUSD);
			} else if (data.getAction().equalsIgnoreCase("S")) {
				settledIncoming = Util.putOrUpdateHashmap(settledIncoming, data.getSettlementDate(), amountUSD);
				settledIncomingTrans = Util.putOrUpdateHashmap(settledIncomingTrans, data.getEntity(), amountUSD);					
			}
		}
		printEntityRankings();
		printDailySettledAmounts();
	}
	
	public void printEntityRankings() {
		//Create a report that shows
		//Ranking of entities based on incoming and outgoing amount. 
		//Eg: If entity foo instructs the highest
		//	amount for a buy instruction, then foo is rank 1 for outgoing
		
		logger.info("Creating Report for Ranking of entities based on incoming and outgoing amount in USD...................");
		logger.info("");
		logger.info("Ranking of entities based on incoming amount");
		printEntityRankingsForTransactionsBasedOnAmount(settledIncomingTrans);
		logger.info("");
		logger.info("Ranking of entities based on outgoing amount");
		printEntityRankingsForTransactionsBasedOnAmount(settledOutgoingTrans);
	}
	public void printEntityRankingsForTransactionsBasedOnAmount(HashMap<String, String> settledTransMap){
		Map<String, String> settledTransTreeMap = new TreeMap<String, String>(settledTransMap);
		int rankNumber = 1;
		for (Map.Entry<String, String> entry : settledTransTreeMap.entrySet()) {
			logger.info(rankNumber+" : "+ entry.getKey() +" : US$"+entry.getValue() );
            rankNumber++;
        }
	}
	
	public void printDailySettledAmounts(){
		logger.info("Crating Report for Daily Settled Amount in USD....................");
		logger.info("");
		logger.info("USD settled incoming everyday");
		printDailySettledAmount(settledIncoming);
		logger.info("");
		logger.info("USD settled outgoing everyday");
		printDailySettledAmount(settledOutgoing);
	}	
	public void printDailySettledAmount(HashMap<Date, String> settledMap){
		Map<Date, String> settledTreeMap = new TreeMap<Date, String>(settledMap);
		for (Map.Entry<Date, String> entry : settledTreeMap.entrySet()) {
			logger.info( Util.sdf_US.format(entry.getKey()) +" : US$"+entry.getValue() );
        }
	}
}
