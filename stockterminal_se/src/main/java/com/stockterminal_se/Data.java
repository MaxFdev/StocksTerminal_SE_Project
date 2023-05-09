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
            }
            else if (one.getLastUseTime() == two.getLastUseTime()) {
                return 1;
            }
            return -1;
        }
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

    }
    
    public Stock live(String ticker) {
        
    }

}
