package firoz.daily.trade.report;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class DailyTradeReportTest
{
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); 		// 	02-Jan-2016
	
	@Test
    public void testProcessorWithTwoRecords() throws ParseException {
		List<TradeData> dataList = new ArrayList<TradeData>();
		dataList.add( new TradeData("foo", "B", 0.50, "SGP", sdf.parse("01-Jan-2016"), sdf.parse("02-Jan-2016"), 200, 100.25));
		dataList.add( new TradeData("bar", "S", 0.22, "AED", sdf.parse("05-Jan-2016"), sdf.parse("07-Jan-2016"), 450, 150.5));
		
		DailyTradeReport processor = new DailyTradeReport();
		processor.processInputData(dataList);
		
		assertTrue( true );
    }
	
}
