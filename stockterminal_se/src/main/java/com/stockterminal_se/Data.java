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
        Collections.sort(returnList, new StockUseComp());
        return returnList;
    }
    
    public Stock query(String ticker) {
        
    }
    
    public List<Stock> query(String[] tickers) {

    }
    
    public void clear() {

    }
    
    public void refresh() {

    }
    
    public boolean remove(String ticker) {

    }
    
    public List<Stock> history() {
        List<Stock> sortedList = Collections.sort(stockList, new StockNameComp());
        return Collections.unmodifiableList(this.stockList);
    }
    
    public Stock live(String ticker) {
        
    }

    // comparators

    class StockUseComp implements Comparator<Stock> {

        private StockUseComp() {
        }

        @Override
        public int compare(Stock one, Stock two) {
            if (one.getLastUseTime() > two.getLastUseTime()) {
                return 1;
            }
            else if (one.getLastUseTime() == two.getLastUseTime()) {
                return 0;
            }
            return -1;
        }
    }

    class StockNameComp implements Comparator<Stock> {

        private StockNameComp() {
        }

        @Override
        public int compare(Stock one, Stock two) {
            return one.symbol().compareTo(two.symbol());
        }
    }

}
