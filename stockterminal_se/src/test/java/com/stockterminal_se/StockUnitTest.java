package com.stockterminal_se;

import static org.junit.Assert.*;

import java.io.*;
import java.net.*;
import java.util.*;

import org.junit.Test;

public class StockUnitTest {

    String open;
    String high;
    String low;
    String price;
    String volume;
    String latestTradingDay;
    String prevClose;
    String changeNum;
    String changePercent;

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
        String[] separateAspects = content.toString().split("\"");

        open = separateAspects[9];
        high = separateAspects[13];
        low = separateAspects[17];
        price = separateAspects[21];
        volume = separateAspects[25];
        latestTradingDay = separateAspects[29];
        prevClose = separateAspects[33];
        changeNum = separateAspects[37];
        changePercent = separateAspects[41];
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
        try {
            testStock.refresh("7AWENLJCVXFOKGP4");
        }
        catch (Exception e) {
            System.out.println("Failed refresh");
            fake = false;
            assertTrue(fake);
        }
        assertEquals(testStock.open(), open);
        assertEquals(testStock.high(), high);
        assertEquals(testStock.low(), low);
        assertEquals(testStock.price(), price);
        assertEquals(testStock.volume(), volume);
        assertEquals(testStock.latestTrading(), latestTradingDay);
        assertEquals(testStock.prevClose(), prevClose);
        assertEquals(testStock.changeNum(), changeNum);
        assertEquals(testStock.changePercent(), changePercent);
    }

    MinHeap<Stock> testHeap = new MinHeap<Stock>();

    // Heap storage check
    @Test
    public void baseKickOutTest() {
        Stock testStock1 = new Stock("AAPL", "7AWENLJCVXFOKGP4");
        Stock testStock2 = new Stock("GOOG", "7AWENLJCVXFOKGP4");
        boolean thrown = false;
        try {
            Stock testStock3 = new Stock("INVALID", "7AWENLJCVXFOKGP4");
        }
        catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert(thrown);
        thrown = false;
        Stock testStock4 = new Stock("TSLA", "7AWENLJCVXFOKGP4");
        Stock testStock5 = new Stock("VOO", "7AWENLJCVXFOKGP4");
        Stock testStock6 = new Stock("AMZN", "7AWENLJCVXFOKGP4");
        Stock testStock7 = new Stock("ABC", "7AWENLJCVXFOKGP4");
        try {
            Stock testStock8 = new Stock("WLMT", "7AWENLJCVXFOKGP4");
        }
        catch (IllegalArgumentException e) {
            thrown = true;
        }
        assert(thrown);
        thrown = false;
        Stock testStock9 = new Stock("VOX", "7AWENLJCVXFOKGP4");
        Stock testStock10 = new Stock("FOX", "7AWENLJCVXFOKGP4");
        testHeap.insert(testStock1);
        testHeap.insert(testStock2);
        testHeap.insert(testStock4);
        testHeap.insert(testStock5);
        testHeap.insert(testStock6);
        testHeap.insert(testStock7);
        testHeap.insert(testStock9);
        testHeap.insert(testStock10);
        Stock testStock11 = new Stock("PANW", "7AWENLJCVXFOKGP4");
        testHeap.insert(testStock11);
        assertEquals(testStock1, testHeap.remove());
        assertEquals(testStock2, testHeap.remove());
        assertEquals(testStock4, testHeap.remove());
        assertEquals(testStock5, testHeap.remove());
        assertEquals(testStock6, testHeap.remove());
        assertEquals(testStock7, testHeap.remove());
        assertEquals(testStock9, testHeap.remove());
        assertEquals(testStock10, testHeap.remove());
        assertEquals(testStock11, testHeap.remove());
    }

    @Test
    public void overloadTest() {
        Stock testStock1 = new Stock("AAPL", "7AWENLJCVXFOKGP4");
        Stock testStock2 = new Stock("GOOG", "7AWENLJCVXFOKGP4");
        Stock testStock3 = new Stock("META", "7AWENLJCVXFOKGP4");
        Stock testStock4 = new Stock("TSLA", "7AWENLJCVXFOKGP4");
        Stock testStock5 = new Stock("VOO", "7AWENLJCVXFOKGP4");
        Stock testStock6 = new Stock("AMZN", "7AWENLJCVXFOKGP4");
        Stock testStock7 = new Stock("ABC", "7AWENLJCVXFOKGP4");
        Stock testStock8 = new Stock("MSFT", "7AWENLJCVXFOKGP4");
        Stock testStock9 = new Stock("VOX", "7AWENLJCVXFOKGP4");
        Stock testStock10 = new Stock("FOX", "7AWENLJCVXFOKGP4");
        testHeap.insert(testStock1);
        testHeap.insert(testStock2);
        testHeap.insert(testStock3);
        testHeap.insert(testStock4);
        testHeap.insert(testStock5);
        testHeap.insert(testStock6);
        testHeap.insert(testStock7);
        testHeap.insert(testStock8);
        testHeap.insert(testStock9);
        testHeap.insert(testStock10);
        Stock testStockExtra = new Stock("NFLX", "7AWENLJCVXFOKGP4");
        testHeap.insert(testStockExtra);
        assertEquals(testHeap.remove(), testStock2);
    }

    // \/ \/ Data test \/ \/
    Data testData = new Data();

    @Test
    public void basicDataTest() {
        Stock testStock2 = new Stock("GOOG", "7AWENLJCVXFOKGP4");
        Stock testStock3 = new Stock("META", "7AWENLJCVXFOKGP4");
        Stock testStock4 = new Stock("TSLA", "7AWENLJCVXFOKGP4");
        Stock testStock5 = new Stock("NFLX", "7AWENLJCVXFOKGP4");
        String[] testArray = {"GOOG", "META", "TSLA"};
        List<Stock> stockList = new ArrayList<Stock>();
        stockList.add(testStock2);
        stockList.add(testStock3);
        stockList.add(testStock4);
        assertEquals(testData.query("NFLX"), testStock5);
        assertEquals(testData.query(testArray), stockList);
        stockList.add(testStock5);
        List<Stock> sortedList = stockList;
        Collections.sort(sortedList, comp);
        System.out.println(testData.history());
        assertEquals(testData.history(), sortedList);
        testData.clear();
        assertEquals(testData.history(), new ArrayList<Stock>());
        assertEquals(testData.live("GOOG"), null);
        testData.query("GOOG");
        assertEquals(testData.live("GOOG"), testStock2);
    }

    private Comparator<Stock> comp = new Comparator<Stock>() {
        @Override
        public int compare(Stock one, Stock two) {
            return one.symbol().compareTo(two.symbol());
        }
    };
}