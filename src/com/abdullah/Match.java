package com.abdullah;

/**
 * Created by asus on 9.12.2015.
 */
public class Match {

    private int matchNum;
    private char uniqueChar;
    private double probability;

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    private String match;

    public boolean isProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    private boolean isProtected;


    public Match(int matchNum, char uniqueChar, double probability,String match) {
        this.matchNum = matchNum;
        this.uniqueChar = uniqueChar;
        this.probability = probability;
        this.match=match;
    }


    public Match(boolean isProtected) {
        this.isProtected=isProtected;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public char getUniqueChar() {
        return uniqueChar;
    }

    public void setUniqueChar(char uniqueChar) {
        this.uniqueChar = uniqueChar;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getTransaction() {
        return transaction;
    }

    public void setTransaction(double transaction) {
        this.transaction = transaction;
    }

    public Match(double transaction, int transactionIndex) {
        this.transaction = transaction;
        this.transactionIndex = transactionIndex;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(int transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    private int transactionIndex;
    private double transaction;
}
