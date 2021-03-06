package com.totalit.bloodbankstatement.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class StockReceivedFromAvailable extends BaseEntity{


    private String receivedFrom;
    private String branchName;


    @ManyToOne
    @JsonIgnore
    private StockAvailable stockAvailable;

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

    public StockAvailable getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(StockAvailable stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}