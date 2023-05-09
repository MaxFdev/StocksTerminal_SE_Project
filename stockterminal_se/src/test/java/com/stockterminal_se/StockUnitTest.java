package com.stockterminal_se;

import static org.junit.Assert.*;

import java.io.*;
import java.net.*;

import org.junit.Test;

public class StockUnitTest {

    public String createAllData() throws Exception {
        String urlString = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=AAPL&apikey=7AWENLJCVXFOKGP4";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        // creates request using stock information and sets the request

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        //creates a BfferedReader for the input stream of the get for information and updates content for it.

        String[] separate = content.toString().split("\"");
        StringBuffer concatenated = new StringBuffer();
        String title;
        String core;
        for (int i = 5; i < 42; i = i + 4) {
            title = separate[i - 2].substring(4);
            core = separate[i];
			if (i == 5) {
				concatenated.append(title + ": " + core);
			}
            else {
            concatenated.append("\n" + title + ": " + core);
			}
        }
        return concatenated.toString();
    }

    // \/ \/ tests for Stock \/ \/ 
    @Test
    public void stockStart() {
        boolean fake = true;
        Stock testStock = new Stock("AAPL", "7AWENLJCVXFOKGP4");
        assertEquals(testStock.symbol(), "AAPL");
        try {
            assertEquals(testStock.allData(), createAllData());
        }
        catch (Exception e) {
            System.out.println("Failed the try/catch in: stockStart");
            fake = false;
            assertTrue(fake);
        }

    }

}
