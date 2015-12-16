package com.abdullah;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 15.12.2015.
 */
public class Delete {

    private double transactionToDelete;

    public int getDeleteNum() {
        return deleteNum;
    }

    public void setDeleteNum(int deleteNum) {
        this.deleteNum = deleteNum;
    }


    public double getTransactionToDelete() {
        return transactionToDelete;
    }

    public void setTransactionToDelete(double transactionToDelete) {
        this.transactionToDelete = transactionToDelete;
    }

    private int deleteNum;



    public Delete( double transactionToDelete, int deleteNum) {
        this.transactionToDelete = transactionToDelete;
        this.deleteNum = deleteNum;
    }
}
