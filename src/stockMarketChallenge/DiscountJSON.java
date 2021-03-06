package stockMarketChallenge;

import java.util.*;

public class DiscountJSON {
	
	public DiscountJSON() {
		
	}

	public DiscountJSON(String toBeParsed, StockObject sObj) {
		String endLine = "\n";
		ArrayList<String> parsedLine = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(toBeParsed, endLine);
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.contains(":")) {
				char quote = '"';
				int firstSep = 0;
				int secSep = 0;
				
				String[] splitString = token.split(": ");
				token = splitString[1];
				firstSep = token.indexOf(quote);
				secSep = token.lastIndexOf(quote);
				
				if (firstSep > 0 || secSep > 0) {
					token = token.substring(firstSep+1, secSep);
					parsedLine.add(token);
				}
			}
		}
		
		sObj.setSymbol(parsedLine.get(0));
		sObj.setOpen(Float.parseFloat(parsedLine.get(1)));
		sObj.setHigh(Float.parseFloat(parsedLine.get(2)));
		sObj.setLow(Float.parseFloat(parsedLine.get(3)));
		sObj.setCurrent(Float.parseFloat(parsedLine.get(4)));
		sObj.setClose(Float.parseFloat(parsedLine.get(7)));
	}
	
	public String toString(StockObject sObj) {
		String outString = "";
		
		outString = "{\n" +
					sObj.toString() +
					"\n}\n\n";
		
		return outString;				
	}
	
}
