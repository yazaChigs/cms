package com.totalit.bloodbankstatement.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class StockReceivedFromQuarantined extends BaseEntity{


    private String receivedFrom;
    private String branchName;


    @ManyToOne
    @JsonIgnore
    private StockQuarantined stockQuarantined;

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

    public StockQuarantined getStockQuarantined() {
        return stockQuarantined;
    }

    public void setStockQuarantined(StockQuarantined stockQuarantined) {
        this.stockQuarantined = stockQuarantined;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}