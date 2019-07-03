package com.totalit.bloodbankstatement.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class StockIssuedToQuarantine extends BaseEntity{


        private String issuedTo;
        @ManyToOne
        @JsonIgnore
        private StockQuarantined stockQuarantined;


    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public StockQuarantined getStockQuarantined() {
        return stockQuarantined;
    }

    public void setStockQuarantined(StockQuarantined stockQuarantined) {
        this.stockQuarantined = stockQuarantined;
    }
}
