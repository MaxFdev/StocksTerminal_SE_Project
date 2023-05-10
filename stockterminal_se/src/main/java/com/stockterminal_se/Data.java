package com.stockterminal_se;

import java.util.*;

public class Data {

    MinHeap<Stock> stockHeap;
    List<Stock> stockList;
    private final String apiKey = "7AWENLJCVXFOKGP4";

    public Data() {
        this.stockHeap = new MinHeap<Stock>();
        this.stockList = new ArrayList<Stock>();
    }
    
    /**
     * This method takes a ticker as a param and returns an updated stock object.
     * 
     * @param ticker
     * @return Stock for given symbol if exists or was otherwise instantiated properly,
     * null otherwise
     * 
     */
    public Stock query(String ticker) {
        Stock returnStock = live(ticker);
        if (returnStock == null) {
            returnStock = createNewStock(ticker);
        }
        return returnStock;
    }
    
    /**
     * This method is the same except it deals with multiple stocks.
     * 
     * @param tickers
     * @return returns a List popualted with all existing symbols (updated),
     * and null if none of them exists
     * 
     */
    public List<Stock> query(String[] tickers) {
        List<Stock> queryList = new ArrayList<Stock>();
        for (String ticker : tickers) {
            if (get(ticker) != null) {
                queryList.add(get(ticker));
            }
        }
        if (queryList.isEmpty()) return null;
        return Collections.unmodifiableList(queryList);
    }

    /**
     * Creates a new stock.
     * 
     * @param ticker
     * @return the newly-created stock, or null if something went awry while trying
     * 
     */
    private Stock createNewStock(String ticker) {
        Stock newStock;
        try {
            newStock = new Stock(ticker, this.apiKey);
            this.stockHeap.insert(newStock);
            this.stockList.add(newStock);
        }
        catch (Exception e) {
            return null;
        }
        return newStock;
    }
    
    /**
     * Clears all the stock objects out of data.
     */
    public void clear() {
        this.stockHeap = new MinHeap<Stock>();
        this.stockList = new ArrayList<Stock>();
    }
    
    /**
     * Refreshes all Stocks being held in the heap.
     * 
     * @return a list of ONLY the refreshed stocks, null if none of
     * the items could be refreshed or there weren't any stocks in the heap.
     * 
     */
    public List<Stock> refresh() {
        List<Stock> returnList = new ArrayList<Stock>();
        if (this.stockHeap.isEmpty()) return null;
        for (Stock stock : this.stockHeap.getElements()) {
            try {
                stock.refresh(this.apiKey);
                returnList.add(stock);
            }
            catch (Exception e) {
            }// just continue along in the heap
        }
        if (returnList.isEmpty()) return null;
        return Collections.unmodifiableList(returnList);
    }
    
    /**
     * Removes the stock completely from all data structures in Data
     * 
     * @param ticker
     * @return true if stock was removed, false if no such stock existed in Data
     * 
     */
    public boolean remove(String ticker) {
        if (get(ticker) != null) {
            get(ticker).setUseTime(0);
            this.stockHeap.reHeapify(get(ticker));
            this.stockHeap.remove();
            return this.stockList.remove(get(ticker));
        }
        return false;
    }
    
    /**
     * Returns a list of all stocks ever called.
     * 
     * @return an unmodifiable list of all stocks ever called, organized by stock
     * ticker symbol alphabetical order
     * 
     */
    public List<Stock> history() {
        List<Stock> sortedList = this.stockList;
        Collections.sort(sortedList, comp);
        return Collections.unmodifiableList(sortedList);
    }
    
    /**
     * Updates and returns an EXISTING stock.
     * 
     * @param ticker
     * @return the stock after refreshing, null if the stock doesn't exist
     * or the query fails
     * 
     */
    public Stock live(String ticker) {
        if (this.stockList.isEmpty()) {
            return null;
        }
        for (Stock stock : this.stockList) {
            if (stock.symbol().equals(ticker))  {
                try {
                    stock.refresh(this.apiKey);
                }
                catch (Exception e){
                    return null;
                }
                return stock;
            }
        }
        return null;
    }

    /**
     * This is a compatator for stocks for sorting by name
     */
    private Comparator<Stock> comp = new Comparator<Stock>() {
        @Override
        public int compare(Stock one, Stock two) {
            return one.symbol().compareTo(two.symbol());
        }
    };

    /**
     * takes the given ticker string and finds a stock for it
     * 
     * @param ticker
     * @return the Stock associated with the string if it exists,
     * otherwise return null
     * 
     */
    private Stock get(String ticker) {
        if (this.stockList.isEmpty()) return null;
        for (Stock stock : this.stockList) { // checking for existing stock
            if (stock.symbol().equals(ticker))  {
                return stock;
            }
        }
        return null;
    }
}