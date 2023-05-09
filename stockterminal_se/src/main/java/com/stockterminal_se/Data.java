package com.stockterminal_se;

import java.util.ArrayList;

public class Data {

    MinHeap<Stock> stockHeap;

    public Data () {
        this.stockHeap = new MinHeap<Stock>();
    }

    public ArrayList getSaved() {
        for (Stock stock : this.stockHeap) {

        }
    }
}
