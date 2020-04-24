package com.yaza.cms.domain.dto;

import com.yaza.cms.domain.config.Task;
import com.yaza.cms.domain.config.User;

import java.io.Serializable;
import java.util.List;

public class AdminStatsDTO implements Serializable {

    private List<String> userNames;
    private List<Integer> resolvedList;
    private List<Integer> pendingList;
    private Integer resolved;
    private Integer pending;
    private List<Double> totalSpentTime;
    private List<Double> expectedTime;
    private List<Double> efficiency;


    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public List<Integer> getResolvedList() {
        return resolvedList;
    }

    public void setResolvedList(List<Integer> resolvedList) {
        this.resolvedList = resolvedList;
    }

    public List<Integer> getPendingList() {
        return pendingList;
    }

    public void setPendingList(List<Integer> pendingList) {
        this.pendingList = pendingList;
    }

    public Integer getResolved() {
        return resolved;
    }

    public void setResolved(Integer resolved) {
        this.resolved = resolved;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public List<Double> getTotalSpentTime() {
        return totalSpentTime;
    }

    public void setTotalSpentTime(List<Double> totalSpentTime) {
        this.totalSpentTime = totalSpentTime;
    }

    public List<Double> getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(List<Double> efficiency) {
        this.efficiency = efficiency;
    }

    public List<Double> getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(List<Double> expectedTime) {
        this.expectedTime = expectedTime;
    }
}
