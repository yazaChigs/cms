package com.totalit.bloodbankstatement.domain.dto;

import java.io.Serializable;

public class StockInfoDTO implements Serializable {
    private Integer demandVsSupply;
    private Integer stockAvailable;
    private Integer supplies;
    private Integer orders;
    private Integer collections;
    private Integer quarantineStock;
    private Integer opening;
    private Integer receipts;
    private Integer issues;
    private Integer discards;



    public Integer getDemandVsSupply() {
        return demandVsSupply;
    }

    public void setDemandVsSupply(Integer demandVsSupply) {
        this.demandVsSupply = demandVsSupply;
    }

    public Integer getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(Integer stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public Integer getSupplies() {
        return supplies;
    }

    public void setSupplies(Integer supplies) {
        this.supplies = supplies;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    public Integer getQuarantineStock() {
        return quarantineStock;
    }

    public void setQuarantineStock(Integer quarantineStock) {
        this.quarantineStock = quarantineStock;
    }

    public Integer getOpening() {
        return opening;
    }

    public void setOpening(Integer opening) {
        this.opening = opening;
    }

    public Integer getReceipts() {
        return receipts;
    }

    public void setReceipts(Integer receipts) {
        this.receipts = receipts;
    }

    public Integer getIssues() {
        return issues;
    }

    public void setIssues(Integer issues) {
        this.issues = issues;
    }

    public Integer getDiscards() {
        return discards;
    }

    public void setDiscards(Integer discards) {
        this.discards = discards;
    }
}
