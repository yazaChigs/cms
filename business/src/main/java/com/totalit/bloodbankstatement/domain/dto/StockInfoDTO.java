package com.totalit.bloodbankstatement.domain.dto;

import java.io.Serializable;

public class StockInfoDTO implements Serializable {
    private Double demandVsSupply;
    private Integer stockAvailable;
    private Integer supplies;
    private Integer orders;
    private Integer collections;
    private Integer collectionsHarare;
    private Integer collectionsBulawayo;
    private Integer collectionsGweru;
    private Integer collectionsMutare;
    private Integer collectionsMasvingo;
    private Integer quarantineStock;
    private Integer opening;
    private Integer receipts;
    private Integer issues;
    private Integer discards;
    private Integer harareTotalMinCap;
    private Integer bulawayoTotalMinCap;
    private Integer gweruTotalMinCap;
    private Integer masvingoTotalMinCap;
    private Integer mutareTotalMinCap;
    private Integer stockedOplus;
    private Integer stockedOminus;
    private Integer stockedAplus;
    private Integer stockedBplus;
    private Integer dailyReqOplus;
    private Integer dailyReqOminus;
    private Integer dailyReqAplus;
    private Integer dailyReqBplus;
    private Double bsms;
    private Integer expired;
    private Integer serum;
    private Integer samplesOnly;



    public Double getDemandVsSupply() {
        return demandVsSupply;
    }

    public void setDemandVsSupply(Double demandVsSupply) {
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

    public Integer getHarareTotalMinCap() {
        return harareTotalMinCap;
    }

    public void setHarareTotalMinCap(Integer harareTotalMinCap) {
        this.harareTotalMinCap = harareTotalMinCap;
    }

    public Integer getBulawayoTotalMinCap() {
        return bulawayoTotalMinCap;
    }

    public void setBulawayoTotalMinCap(Integer bulawayoTotalMinCap) {
        this.bulawayoTotalMinCap = bulawayoTotalMinCap;
    }

    public Integer getGweruTotalMinCap() {
        return gweruTotalMinCap;
    }

    public void setGweruTotalMinCap(Integer gweruTotalMinCap) {
        this.gweruTotalMinCap = gweruTotalMinCap;
    }

    public Integer getMasvingoTotalMinCap() {
        return masvingoTotalMinCap;
    }

    public void setMasvingoTotalMinCap(Integer masvingoTotalMinCap) {
        this.masvingoTotalMinCap = masvingoTotalMinCap;
    }

    public Integer getMutareTotalMinCap() {
        return mutareTotalMinCap;
    }

    public void setMutareTotalMinCap(Integer mutareTotalMinCap) {
        this.mutareTotalMinCap = mutareTotalMinCap;
    }

    public Integer getCollectionsHarare() {
        return collectionsHarare;
    }

    public void setCollectionsHarare(Integer collectionsHarare) {
        this.collectionsHarare = collectionsHarare;
    }

    public Integer getCollectionsBulawayo() {
        return collectionsBulawayo;
    }

    public void setCollectionsBulawayo(Integer collectionsBulawayo) {
        this.collectionsBulawayo = collectionsBulawayo;
    }

    public Integer getCollectionsGweru() {
        return collectionsGweru;
    }

    public void setCollectionsGweru(Integer collectionsGweru) {
        this.collectionsGweru = collectionsGweru;
    }

    public Integer getCollectionsMutare() {
        return collectionsMutare;
    }

    public void setCollectionsMutare(Integer collectionsMutare) {
        this.collectionsMutare = collectionsMutare;
    }

    public Integer getCollectionsMasvingo() {
        return collectionsMasvingo;
    }

    public void setCollectionsMasvingo(Integer collectionsMasvingo) {
        this.collectionsMasvingo = collectionsMasvingo;
    }

    public Integer getStockedOplus() {
        return stockedOplus;
    }

    public void setStockedOplus(Integer stockedOplus) {
        this.stockedOplus = stockedOplus;
    }

    public Integer getStockedOminus() {
        return stockedOminus;
    }

    public void setStockedOminus(Integer stockedOminus) {
        this.stockedOminus = stockedOminus;
    }

    public Integer getStockedAplus() {
        return stockedAplus;
    }

    public void setStockedAplus(Integer stockedAplus) {
        this.stockedAplus = stockedAplus;
    }

    public Integer getStockedBplus() {
        return stockedBplus;
    }

    public void setStockedBplus(Integer stockedBplus) {
        this.stockedBplus = stockedBplus;
    }

    public Integer getDailyReqOplus() {
        return dailyReqOplus;
    }

    public void setDailyReqOplus(Integer dailyReqOplus) {
        this.dailyReqOplus = dailyReqOplus;
    }

    public Integer getDailyReqOminus() {
        return dailyReqOminus;
    }

    public void setDailyReqOminus(Integer dailyReqOminus) {
        this.dailyReqOminus = dailyReqOminus;
    }

    public Integer getDailyReqAplus() {
        return dailyReqAplus;
    }

    public void setDailyReqAplus(Integer dailyReqAplus) {
        this.dailyReqAplus = dailyReqAplus;
    }

    public Integer getDailyReqBplus() {
        return dailyReqBplus;
    }

    public void setDailyReqBplus(Integer dailyReqBplus) {
        this.dailyReqBplus = dailyReqBplus;
    }

    public Double getBsms() {
        return bsms;
    }

    public void setBsms(Double bsms) {
        this.bsms = bsms;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getSerum() {
        return serum;
    }

    public void setSerum(Integer serum) {
        this.serum = serum;
    }

    public Integer getSamplesOnly() {
        return samplesOnly;
    }

    public void setSamplesOnly(Integer samplesOnly) {
        this.samplesOnly = samplesOnly;
    }
}
