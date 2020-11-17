package stockMarketChallenge;

public class StockObject {
	
	private String symbol, open, high, low, current, pClose;
	
	StockObject(){
		
	}
	StockObject(String symbol, String open, String high, String low, String current, String pClose){
		this.symbol = symbol;
		this.open = open;
		this.high = high;
		this.low = low;
		this.current = current;
		this.pClose = pClose;
	}
	
	//GETTERS
	public String getSymbol() {
		return symbol;
	}
	public String getOpen() {
		return open;
	}
	public String getHigh() {
		return high;
	}
	public String getLow() {
		return low;
	}
	public String getCurrent() {
		return current;
	}
	public String getClose() {
		return pClose;
	}

	
	//SETTERS
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public void setClose(String pClose) {
		this.pClose = pClose;
	}
	
	
	public String toString() {
		return "Symbol: " + this.symbol + "\nClose: " + this.pClose + "\nOpen: " + this.open + "\nHigh: " + this.high + "\nLow: " + this.low + "\nCurrent: " + this.current;
	}
	

}
