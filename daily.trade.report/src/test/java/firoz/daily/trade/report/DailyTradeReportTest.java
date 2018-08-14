package firoz.daily.trade.report;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
	
	@Test
    public void testProcessorWithMultipleRecords() throws ParseException {
		List<TradeData> dataList = new ArrayList<TradeData>();
		dataList.add( new TradeData("foo", "B", 0.50, "SGP", Util.getDateFromString("01-Jan-2016"), Util.getDateFromString("02-Jan-2016"), 200, 100.25));
		dataList.add( new TradeData("foo", "S", 0.45, "ASK", Util.getDateFromString("01/01/2016"), Util.getDateFromString("01/04/2016"), 100, 40.30));
		dataList.add( new TradeData("foo", "B", 0.50, "SGP", Util.getDateFromString("01-Jan-2016"), Util.getDateFromString("03-Jan-2016"), 150, 23.55));
		dataList.add( new TradeData("bar", "S", 0.22, "AED", Util.getDateFromString("05-Jan-2016"), Util.getDateFromString("07-Jan-2016"), 450, 150.5));		
		dataList.add( new TradeData("bar", "S", 0.22, "AED", Util.getDateFromString("05-Jan-2016"), Util.getDateFromString("07-Jan-2016"), 20, 45.20));
		dataList.add( new TradeData("bar", "S", 0.22, "AED", Util.getDateFromString("06-Jan-2016"), Util.getDateFromString("07-Jan-2016"), 132, 125.00));
		dataList.add( new TradeData("bar", "B", 0.22, "AED", Util.getDateFromString("07-Jan-2016"), Util.getDateFromString("08-Jan-2016"), 455, 345.75));
		dataList.add( new TradeData("foo", "B", 0.18, "SAR", Util.getDateFromString("01-Jan-2016"), Util.getDateFromString("02-Jan-2016"), 191, 20.80));
		dataList.add( new TradeData("bar", "B", 0.18, "SAR", Util.getDateFromString("01-Jan-2016"), Util.getDateFromString("01-Jan-2016"), 380, 150.5));
		
		DailyTradeReport processor = new DailyTradeReport();
		processor.processInputData(dataList);
		
		assertTrue( true );
    }
    
	@Test (expected = ParseException.class)
    public void testInputDataError1() throws ParseException {
		List<TradeData> dataList = new ArrayList<TradeData>();
		dataList.add( new TradeData("foo", "B", 0.50, "SGP", Util.getDateFromString("01/Jan/2016"), Util.getDateFromString("02-Jan-2016"), 200, 100.25));
		dataList.add( new TradeData("bar", "S", 0.22, "AED", Util.getDateFromString("05-Jan-2016"), Util.getDateFromString("07-Jan-2016"), 450, 150.5));
		
		DailyTradeReport processor = new DailyTradeReport();
		processor.processInputData(dataList);
		fail();
    }
	
	@Test (expected = ParseException.class)
    public void testInputDataError2() throws ParseException {
		List<TradeData> dataList = new ArrayList<TradeData>();
		dataList.add( new TradeData("foo", "B", 0.50, "SGP", Util.sdf.parse("01/Jan/2016"), Util.sdf.parse("02-Jan-2016"), 0, 100.25));
		dataList.add( new TradeData("bar", "S", 0, "AED", Util.sdf.parse("05-Jan-2016"), Util.sdf.parse("07-Jan-2016"), 450, 150.5));
		
		DailyTradeReport processor = new DailyTradeReport();
		processor.processInputData(dataList);
		fail();
    }
	
	@Test (expected = AssertionError.class)
    public void testInputDataError3() throws Exception {
		List<TradeData> dataList = new ArrayList<TradeData>();
		
		DailyTradeReport processor = new DailyTradeReport();
		processor.processInputData(dataList);
		fail();
    }
	
	@Test (expected = ParseException.class)
    public void testDateParsing() throws ParseException {
		Util.getDateFromString("01/Jan/2016");
		fail();
	}
	
}
