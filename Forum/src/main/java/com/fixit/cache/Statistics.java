package com.fixit.cache;

public class Statistics {

    private long counterUsers;
    private long counterResults;
    private long counterBloodResults;
    private long counterIrmResults;

    public Statistics() {
    }

    public long getCounterUsers() {
        return counterUsers;
    }

    public void setCounterUsers(long counterUsers) {
        this.counterUsers = counterUsers;
    }

    public long getCounterResults() {
        return counterResults;
    }

    public void setCounterResults(long counterResults) {
        this.counterResults = counterResults;
    }

    public long getCounterBloodResults() {
        return counterBloodResults;
    }

    public void setCounterBloodResults(long counterBloodResults) {
        this.counterBloodResults = counterBloodResults;
    }

    public long getCounterIrmResults() {
        return counterIrmResults;
    }

    public void setCounterIrmResults(long counterIrmResults) {
        this.counterIrmResults = counterIrmResults;
    }
}
