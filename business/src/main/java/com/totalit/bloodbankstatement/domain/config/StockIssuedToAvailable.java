package com.totalit.bloodbankstatement.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class StockIssuedToAvailable extends BaseEntity{

        private String branchName;
        private String issuedTo;
        @ManyToOne
        @JsonIgnore
        private StockAvailable stockAvailable;


    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
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
