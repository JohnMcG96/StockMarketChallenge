package stockMarketChallenge;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.file.*;
import java.util.*;

public class StockMain {
	 static Scanner console = new Scanner (System.in);
	 static String API_KEY = "";
	 
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String symbol="";
		String stockResponse = "";
		String failedResponse = "{\n"
				+ "    \"Global Quote\": {}\n"
				+ "}";
		StockObject sObj = new StockObject();
		Path tempFile = Files.createTempFile(null, null);
		char repeat = 0, cont = 'Y', end = 'N';
		ArrayList<String> foundInFile = new ArrayList<String>();
		boolean fileFound = false;
		
		try (InputStream input = new FileInputStream("src/resources/config.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            API_KEY = prop.getProperty("API_KEY");
		} catch (IOException ex) {
            ex.printStackTrace();
        }
		
		
		
		do {
			System.out.println("\nJohn Stock Market Challenege");
			
			symbol = ReadValidSymbol();
			
			fileFound = FindInFile(tempFile.toFile(), symbol, foundInFile);
			
			if (fileFound == false) {
				PopulateStock(stockResponse, failedResponse, symbol, sObj, tempFile);
			}
			else if (fileFound == true) {
				char refResp = 0;
				
				System.out.print("\nStock already exists in file, would you like to refresh? (Y/N): ");
				refResp = console.next().charAt(0);
				refResp = Character.toUpperCase(refResp);
				while ((refResp != cont) && (refResp != end)) {
					if ((refResp != cont) || (refResp != end)) {
						System.out.println("\nInput not recognised, please try again.");
						System.out.print("Would you like to refresh? (Y/N): ");
						repeat = console.next().charAt(0);
						repeat = Character.toUpperCase(repeat);
					}
				};
				
				if (refResp == cont) {
					PopulateStock(stockResponse, failedResponse, symbol, sObj, tempFile);
				}
				else {
					for (int i = 0; i < foundInFile.size(); i++)  
			            System.out.print(foundInFile.get(i) + "\n");
				}
			}
			
			System.out.print("\nWould you like to enter another Stock symbol? (Y/N): ");
			repeat = console.next().charAt(0);
			repeat = Character.toUpperCase(repeat);
			while ((repeat != cont) && (repeat != end)) {
				if ((repeat != cont) || (repeat != end)) {
					System.out.println("\nInput not recognised, please try again.");
					System.out.print("Would you like to enter another Stock symbol? (Y/N): ");
					repeat = console.next().charAt(0);
					repeat = Character.toUpperCase(repeat);
				}
			};
			
			foundInFile.clear();
		
		} while (repeat == cont);
	}
	
	public static String ReadValidSymbol() {
		String regex = "([A-Z]{1,7})\\w+",
				input = "";
		do {
			System.out.print("Please enter the symbol of the stock you wish to find: ");
			input = console.next();
			input=input.toUpperCase();
			if (!input.matches(regex)) {
				System.out.println("Symbol not valid, please try again.");
			}
		} while (!input.matches(regex));
		
		return input;
	}	
	
	public static boolean FindInFile(File tempFile, String symbol, ArrayList<String> foundInFile) {
		try {
			Scanner scanner = new Scanner(tempFile);
		    //now read the file line by line...
		    int lineNum = 0;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        if(line.contains(symbol)) { 
		            for (int i=lineNum; i<lineNum+10; i++) {
		            	foundInFile.add(line.toString());
		            	line = scanner.nextLine();
		            }
		            scanner.close();
		            return true;
		        }
		    }
		    scanner.close();
		} catch(FileNotFoundException e) {
		}
		return false;
	}
	
	public static void PopulateStock(String stockResponse, String failedResponse, String symbol, StockObject sObj, Path tempFile) throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol="+symbol))
				.header("x-rapidapi-key", API_KEY)
				.header("x-rapidapi-host", "alpha-vantage.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		stockResponse = response.body();
		if (stockResponse.equals(failedResponse)) {
			System.out.print("\nError! Stock Symbol not found, please try again.");
		}
		else {
			stockResponse.toString();
			DiscountJSON tbp = new DiscountJSON();
			tbp.DiscountJSON(stockResponse, sObj);
			try {
				Files.write(Paths.get(tempFile.toString()), stockResponse.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

