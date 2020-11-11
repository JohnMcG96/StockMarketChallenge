package stockMarketChallenge;

import java.util.*;

public class DiscountJSON {

	public void DiscountJSON(String toBeParsed, StockObject sObj) {
		String endLine = "\n";
		ArrayList<String> parsedLine = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(toBeParsed, endLine);
		while (st.hasMoreTokens()) {
			parsedLine.add(st.nextToken());
		}
		
		sObj.setSymbol(parsedLine.get(2));
		sObj.setOpen(parsedLine.get(3));
		sObj.setHigh(parsedLine.get(4));
		sObj.setLow(parsedLine.get(5));
		sObj.setCurrent(parsedLine.get(6));
		sObj.setClose(parsedLine.get(9));
		
		System.out.println(sObj.toString());
		
	}
	
}
