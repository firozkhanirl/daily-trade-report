package firoz.daily.trade.report;

import java.util.List;

import org.apache.log4j.Logger;

import firoz.daily.trade.report.TradeData;


public class DailyTradeReport 
{
	static Logger logger = Logger.getLogger(DailyTradeReport.class);

	/**
	   * This method should read input records and do further operation
	   */
	public void processInputData(List<TradeData> dataList) {
		logger.info("Processing Input Data.............");
		
		for(TradeData data : dataList) {
			logger.debug(data);
		}
	}
	
	
}
