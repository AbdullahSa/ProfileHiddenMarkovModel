package com.abdullah;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 15.12.2015.
 */
public class Insert {

    public Insert(double transactionToInsert, int insertNum) {
        this.transactionToInsert = transactionToInsert;
        this.insertNum = insertNum;
    }

    public double getTransactionToInsert() {
        return transactionToInsert;
    }

    public void setTransactionToDelete(double transactionToInsert) {
        this.transactionToInsert = transactionToInsert;
    }

    public int getInsertNum() {
        return insertNum;
    }

    public void setInsertNum(int insertNum) {
        this.insertNum = insertNum;
    }

    private double transactionToInsert;
    private int insertNum;


    public void setTransactionToInsert(double transactionToInsert) {
        this.transactionToInsert = transactionToInsert;
    }

}