package com.yaza.cms.domain.dto;

import com.yaza.cms.domain.config.Branch;

import java.io.Serializable;
import java.util.List;

public class StatsDTO implements Serializable {
    private Integer waiting;
    private Integer pending;
    private Integer resolved;
    private Integer smartCash;
    private Integer mainCard;
    private Integer visaCard;
    private List<Integer> mobileQueries;
    private List<Integer> cardQueries;
    private List<String> mobileQueriesNames;
    private List<String> cardQueriesNames;

    public List<Integer> getCardQueries() {
        return cardQueries;
    }

    public void setCardQueries(List<Integer> cardQueries) {
        this.cardQueries = cardQueries;
    }

    public List<String> getMobileQueriesNames() {
        return mobileQueriesNames;
    }

    public void setMobileQueriesNames(List<String> mobileQueriesNames) {
        this.mobileQueriesNames = mobileQueriesNames;
    }

    public List<String> getCardQueriesNames() {
        return cardQueriesNames;
    }

    public void setCardQueriesNames(List<String> cardQueriesNames) {
        this.cardQueriesNames = cardQueriesNames;
    }

    public Integer getWaiting() {
        return waiting;
    }

    public void setWaiting(Integer waiting) {
        this.waiting = waiting;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getResolved() {
        return resolved;
    }

    public void setResolved(Integer resolved) {
        this.resolved = resolved;
    }

    public Integer getSmartCash() {
        return smartCash;
    }

    public void setSmartCash(Integer smartCash) {
        this.smartCash = smartCash;
    }

    public Integer getMainCard() {
        return mainCard;
    }

    public void setMainCard(Integer mainCard) {
        this.mainCard = mainCard;
    }

    public Integer getVisaCard() {
        return visaCard;
    }

    public void setVisaCard(Integer visaCard) {
        this.visaCard = visaCard;
    }

    public List<Integer> getMobileQueries() {
        return mobileQueries;
    }

    public void setMobileQueries(List<Integer> mobileQueries) {
        this.mobileQueries = mobileQueries;
    }
}
