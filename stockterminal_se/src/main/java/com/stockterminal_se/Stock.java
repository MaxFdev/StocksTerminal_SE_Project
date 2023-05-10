package com.stockterminal_se;

import java.io.*;
import java.net.*;

public class Stock implements Comparable<Stock> {

    // all information, formatted
    private String allData;

    // values for individual information
    private String ticker;
    private String open;
    private String high;
    private String low;
    private String price;
    private String volume;
    private String latestTradingDay;
    private String prevClose;
    private String changeNum;
    private String changePercent;

    //time value
    private long lastUseTime;
    
    public Stock (String ticker, String apiKey) {
        this.ticker = ticker;
        try {
            refresh(apiKey);
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }
        setUseTime();
    }

    @Override
    public String toString() {
        return this.allData;
    }

    protected void refresh(String apiKey) throws IOException {
        String rawData = makeCall(apiKey); // creates a String of ALL data
        setVariables(rawData);
        setUseTime();
    }

    // time-use methods and the compareTo associated with it
    public void setUseTime() {
        this.lastUseTime = System.currentTimeMillis();
    }

    public void setUseTime(int value) {
        this.lastUseTime = value;
    }

    public long getLastUseTime() {
        return this.lastUseTime;
    }

    @Override
    public int compareTo(Stock o) {
        return (this.lastUseTime < o.getLastUseTime()) ? -1 : ((this.lastUseTime == o.getLastUseTime()) ? 0: 1);
    }


    // helper methods

    private String makeCall(String apiKey) throws IOException {
        String urlString = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + this.ticker + "&apikey=" + apiKey;
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

        return content.toString();
        //returns content as a String.
    }

    private void setVariables(String info) {
        String[] separateAspects = info.split("\"");

        /*
        indexes are based on testing the split of information
        received in call
        */
        this.open = separateAspects[9];
        this.high = separateAspects[13];
        this.low = separateAspects[17];
        this.price = separateAspects[21];
        this.volume = separateAspects[25];
        this.latestTradingDay = separateAspects[29];
        this.prevClose = separateAspects[33];
        this.changeNum = separateAspects[37];
        this.changePercent = separateAspects[41];

        createAllData(separateAspects);
    }

    private void createAllData(String[] separate) {
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
        this.allData = concatenated.toString();
    }

    //getters
    protected String allData() {
        return this.allData;
    }

    protected String symbol() {
        return this.ticker;
    }

    protected String open() {
        return this.open;
    }

    protected String high() {
        return this.high;
    }

    protected String low() {
        return this.low;
    }

    protected String price() {
        return this.price;
    }

    protected String volume() {
        return this.volume;
    }

    protected String latestTrading() {
        return this.latestTradingDay;
    }

    protected String prevClose() {
        return this.prevClose;
    }

    protected String changeNum() {
        return this.changeNum;
    }

    protected String changePercent() {
        return this.changePercent;
    }
}
