package com.stockterminal_se;

import java.util.Iterator;
import java.util.NoSuchElementException;
import com.stockterminal_se.Stock;

public class MinHeap<com.stockterminal_se.Stock> implements Iterable {
    protected Stock[] elements;
    protected int count = 0;

    public MinHeap() {
        this.elements = (Stock[]) new Comparable[5];
    }

    public void reHeapify(Stock element) {
        if (element == null) throw new NoSuchElementException();
        downHeap(getArrayIndex(element));
    }

    protected int getArrayIndex(Stock element) {
        if (isEmpty()) throw new NoSuchElementException("Heap Is Empty");
        if (element == null) throw new IllegalArgumentException();
        for (int index = 0; index < this.count + 1; index++) {
            if (this.elements[index] == (element)) {
                return index;
            }
        }
        throw new NoSuchElementException();
    }

    protected void doubleArraySize() {
        Stock[] tempArray = (Stock[]) new Comparable[this.elements.length * 2];
        for (int i = 0; i < this.elements.length; i++) {
            tempArray[i] = this.elements[i];
        }
        this.elements = tempArray;
    }

    protected boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * is elements[i] > elements[j]?
     */
    protected boolean isGreater(int i, int j) {
        return ((com.stockterminal_se.Stock) this.elements[i]).compareTo((com.stockterminal_se.Stock) this.elements[j]) > 0;
    }

    /**
     * swap the values stored at elements[i] and elements[j]
     */
    protected void swap(int i, int j) {
        Stock temp = this.elements[i];
        this.elements[i] = this.elements[j];
        this.elements[j] = temp;
    }

    /**
     * while the key at index k is less than its
     * parent's key, swap its contents with its parent’s
     */
    protected void upHeap(int k) {
        while (k > 1 && this.isGreater(k / 2, k)) {
            this.swap(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * move an element down the heap until it is less than
     * both its children or is at the bottom of the heap
     */
    protected void downHeap(int k) {
        while (2 * k <= this.count) {
            //identify which of the 2 children are smaller
            int j = 2 * k;
            if (j < this.count && this.isGreater(j, j + 1)) {
                j++;
            }
            //if the current value is < the smaller child, we're done
            if (!this.isGreater(k, j)) {
                break;
            }
            //if not, swap and continue testing
            this.swap(k, j);
            k = j;
        }
    }

    public void insert(Stock x) {
        // double size of array if necessary
        if (this.count >= this.elements.length - 1) {
            this.doubleArraySize();
        }
        //add x to the bottom of the heap
        this.elements[++this.count] = x;
        //percolate it up to maintain heap order property
        this.upHeap(this.count);
    }

    public Stock remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        Stock min = this.elements[1];
        //swap root with last, decrement count
        this.swap(1, this.count--);
        //move new root down as needed
        this.downHeap(1);
        this.elements[this.count + 1] = null; //null it to prepare for GC
        return min;
    }

    @Override
    public Iterator<Stock> iterator() {
        Iterator<Stock> it = new Iterator<Stock>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < count && elements[currentIndex] != null;
            }

            @Override
            public Stock next() {
                return elements[currentIndex++];
            }
        };
        return it;
    }
}