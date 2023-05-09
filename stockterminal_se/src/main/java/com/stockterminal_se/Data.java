package com.stockterminal_se;

import java.util.*;

public class Data {

    MinHeap<Stock> stockHeap;
    List<Stock> stockList;

    public Data() {
        this.stockHeap = new MinHeap<Stock>();
        this.stockList = new ArrayList<Stock>();
    }

    public ArrayList<Stock> getSaved() {
        ArrayList<Stock> returnList = new ArrayList<Stock>();
        for (Stock stock : this.stockHeap.getElements()) {
            returnList.add(stock);
        }
        Collections.sort(returnList, new StockComp());
        return returnList;
    }

    class StockComp implements Comparator<Stock> {

        private StockComp() {
        }

        @Override
        public int compare(Stock one, Stock two) {
            if (one.getLastUseTime() > two.getLastUseTime()) {
                return 1;
            } else if (one.getLastUseTime() == two.getLastUseTime()) {
                return 1;
            }
            return -1;
        }
    }
    
    /**
     * This method takes a ticker as a param and returns an updated stock object.
     * 
     * @param ticker
     * @return
     */
    public Stock query(String ticker) {
        
    }
    
    /**
     * This method is the same except it deals with multiple stocks.
     * 
     * @param tickers
     * @return
     */
    public List<Stock> query(String[] tickers) {

    }
    
    /**
     * This method clears all the stock objects out of data.
     */
    public void clear() {

    }
    
    /**
     * This method refreshes all Stocks being held in the heap.
     */
    public void refresh() {

    }
    
    /**
     * This method removes a specific stock from data.
     * 
     * @param ticker
     * @return
     */
    public boolean remove(String ticker) {

    }
    
    /**
     * Returns a list of all stocks ever called.
     * 
     * @return
     */
    public List<Stock> history() {
        return Collections.unmodifiableList(this.stockList);
    }
    
    /**
     * This method takes one stock that will be updated to provide live data.
     * 
     * @param ticker
     * @return
     */
    public Stock live(String ticker) {
        
    }
}
