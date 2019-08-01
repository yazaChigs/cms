package com.totalit.bloodbankstatement.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
public class StockAvailable extends BaseEntity {

    @Temporal(TemporalType.DATE)
    private Date todaysDate;
    private Integer openingStock;
    private Integer receivedFromQuarantine;
    private Integer totalAvailable;
    private Integer totalReceipts;
    private Integer hospitals;
    private Integer receicedFromQuarantine;
    private Integer issueToCompats;
    private Integer expired;
    private Integer disasters;
    private Integer haemolysed_clots_other;
    private Integer wholeBloodToPackedCells;
    private Integer totalIssues;
    private Integer totalHospitalOrders;
    private Integer totalTransfersToOtherBranches;
    private Integer currentAvailableStock;
    private Integer rhPositiveWbO;
    private Integer rhPositivePcO;
    private Integer rhPositivePaedWbO;
    private Integer rhPositivePaedPcO;
    private Integer rhNegativeWbO;
    private Integer rhNegativePcO;
    private Integer rhNegativePaedWbO;
    private Integer rhNegativePaedPcO;
    private Integer totalO;
    private Integer percentagefTotalO;
    private Integer rhPositiveWbA;
    private Integer rhPositivePcA;
    private Integer rhPositivePaedWbA;
    private Integer rhPositivePaedPcA;
    private Integer rhNegativeWbA;
    private Integer rhNegativePcA;
    private Integer rhNegativePaedWbA;
    private Integer rhNegativePaedPcA;
    private Integer totalA;
    private Integer percentageOfTotalA;
    private Integer rhPositiveWbB;
    private Integer rhPositivePcB;
    private Integer rhPositivePaedWbB;
    private Integer rhPositivePaedPcB;
    private Integer rhNegativeWbB;
    private Integer rhNegativePcB;
    private Integer rhNegativePaedWbB;
    private Integer rhNegativePaedPcB;
    private Integer totalB;
    private Integer percentageOfTotalB;
    private Integer rhPositiveWbAB;
    private Integer rhPositivePcAB;
    private Integer rhPositivePaedWbAB;
    private Integer rhPositivePaedPcAB;
    private Integer rhNegativeWbAB;
    private Integer rhNegativePcAB;
    private Integer rhNegativePaedWbAB;
    private Integer rhNegativePaedPcAB;
    private Integer totalAB;
    private Integer percentageOfTotalAB;
    private Integer totalRhPositiveWb;
    private Integer totalRhPositivePc;
    private Integer totalRhPositivePaedWb;
    private Integer totalRhPositivePaedPc;
    private Integer totalRhNegativeWb;
    private Integer totalRhNegativePc;
    private Integer totalRhNegativePaedWb;
    private Integer totalRhNegativePaedPc;
    private Integer totalTotal;
    private Integer totalPercentageOfTotal;
    private Integer rhPositiveWbOcompatibility;
    private Integer rhPositivePcOcompatibility;
    private Integer rhPositivePaedWbOcompatibility;
    private Integer rhPositivePaedPcOcompatibility;
    private Integer rhNegativeWbOcompatibility;
    private Integer rhNegativePcOcompatibility;
    private Integer rhNegativePaedWbOcompatibility;
    private Integer rhNegativePaedPcOcompatibility;
    private Integer totalOcompatibility;
    private Integer percentageOfTotalOcompatibility;
    private Integer rhPositiveWbAcompatibility;
    private Integer rhPositivePcAcompatibility;
    private Integer rhPositivePaedWbAcompatibility;
    private Integer rhPositivePaedPcAcompatibility;
    private Integer rhNegativeWbAcompatibility;
    private Integer rhNegativePcAcompatibility;
    private Integer rhNegativePaedWbAcompatibility;
    private Integer rhNegativePaedPcAcompatibility;
    private Integer totalAcompatibility;
    private Integer percentageOfTotalAcompatibility;
    private Integer rhPositiveWbBcompatibility;
    private Integer rhPositivePcBcompatibility;
    private Integer rhPositivePaedWbBcompatibility;
    private Integer rhPositivePaedPcBcompatibility;
    private Integer rhNegativeWbBcompatibility;
    private Integer rhNegativePcBcompatibility;
    private Integer rhNegativePaedWbBcompatibility;
    private Integer rhNegativePaedPcBcompatibility;
    private Integer totalBcompatibility;
    private Integer percentageOfTotalBcompatibility;
    private Integer rhPositiveWbABcompatibility;
    private Integer rhPositivePcABcompatibility;
    private Integer rhPositivePaedWbABcompatibility;
    private Integer rhPositivePaedPcABcompatibility;
    private Integer rhNegativeWbABcompatibility;
    private Integer rhNegativePcABcompatibility;
    private Integer rhNegativePaedWbABcompatibility;
    private Integer rhNegativePaedPcABcompatibility;
    private Integer totalABcompatibility;
    private Integer percentageOfTotalABcompatibility;
    private Integer totalRhPositiveWbcompatibility;
    private Integer totalRhPositivePccompatibility;
    private Integer totalRhPositivePaedWbcompatibility;
    private Integer totalRhPositivePaedPccompatibility;
    private Integer totalRhNegativeWbcompatibility;
    private Integer totalRhNegativePccompatibility;
    private Integer totalRhNegativePaedWbcompatibility;
    private Integer totalRhNegativePaedPccompatibility;
    private Integer totalTotalcompatibility;
    private Integer totalPercentageOfTotalcompatibility;
    private Integer compatsIssues;
    private Integer compatsOrders;
    private Integer compatsPercentageSupply_Orders;
    private  Integer verallSupplies;
    private Integer overallOrders;
    private Integer overallDemandVsSupply;
    private Integer ffp1;
    private Integer plt1;
    private Integer plt2;
    private Integer cryo;
    private Integer paedPacks;

    @JsonIgnore
    @OneToMany(mappedBy = "stockAvailable", fetch = FetchType.EAGER)
    private Set<StockReceivedFromAvailable> stockReceivedFromAvailable;

    @Transient
    public List<StockReceivedFromAvailable> receivedFromAvailable;

    @JsonIgnore
    @OneToMany(mappedBy = "stockAvailable", fetch = FetchType.EAGER)
    private Set<StockIssuedToAvailable> stockIssuedToAvailable;

    @Transient
    public List<StockIssuedToAvailable> issuedToAvailable;

    @ManyToOne
    private Branch branch;

    public Date getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(Date todaysDate) {
        this.todaysDate = todaysDate;
    }

    public Integer getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(Integer openingStock) {
        this.openingStock = openingStock;
    }

    public Integer getReceivedFromQuarantine() {
        return receivedFromQuarantine;
    }

    public void setReceivedFromQuarantine(Integer receivedFromQuarantine) {
        this.receivedFromQuarantine = receivedFromQuarantine;
    }

    public Integer getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(Integer totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public Integer getHospitals() {
        return hospitals;
    }

    public void setHospitals(Integer hospitals) {
        this.hospitals = hospitals;
    }

    public Integer getReceicedFromQuarantine() {
        return receicedFromQuarantine;
    }

    public void setReceicedFromQuarantine(Integer receicedFromQuarantine) {
        this.receicedFromQuarantine = receicedFromQuarantine;
    }

    public Integer getIssueToCompats() {
        return issueToCompats;
    }

    public void setIssueToCompats(Integer issueToCompats) {
        this.issueToCompats = issueToCompats;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getDisasters() {
        return disasters;
    }

    public void setDisasters(Integer disasters) {
        this.disasters = disasters;
    }

    public Integer getHaemolysed_clots_other() {
        return haemolysed_clots_other;
    }

    public void setHaemolysed_clots_other(Integer haemolysed_clots_other) {
        this.haemolysed_clots_other = haemolysed_clots_other;
    }

    public Integer getWholeBloodToPackedCells() {
        return wholeBloodToPackedCells;
    }

    public void setWholeBloodToPackedCells(Integer wholeBloodToPackedCells) {
        this.wholeBloodToPackedCells = wholeBloodToPackedCells;
    }

    public Integer getTotalIssues() {
        return totalIssues;
    }

    public void setTotalIssues(Integer totalIssues) {
        this.totalIssues = totalIssues;
    }

    public Integer getTotalHospitalOrders() {
        return totalHospitalOrders;
    }

    public void setTotalHospitalOrders(Integer totalHospitalOrders) {
        this.totalHospitalOrders = totalHospitalOrders;
    }

    public Integer getRhPositiveWbO() {
        return rhPositiveWbO;
    }

    public void setRhPositiveWbO(Integer rhPositiveWbO) {
        this.rhPositiveWbO = rhPositiveWbO;
    }

    public Integer getRhPositivePcO() {
        return rhPositivePcO;
    }

    public void setRhPositivePcO(Integer rhPositivePcO) {
        this.rhPositivePcO = rhPositivePcO;
    }

    public Integer getRhPositivePaedWbO() {
        return rhPositivePaedWbO;
    }

    public void setRhPositivePaedWbO(Integer rhPositivePaedWbO) {
        this.rhPositivePaedWbO = rhPositivePaedWbO;
    }

    public Integer getRhPositivePaedPcO() {
        return rhPositivePaedPcO;
    }

    public void setRhPositivePaedPcO(Integer rhPositivePaedPcO) {
        this.rhPositivePaedPcO = rhPositivePaedPcO;
    }

    public Integer getRhNegativeWbO() {
        return rhNegativeWbO;
    }

    public void setRhNegativeWbO(Integer rhNegativeWbO) {
        this.rhNegativeWbO = rhNegativeWbO;
    }

    public Integer getRhNegativePcO() {
        return rhNegativePcO;
    }

    public void setRhNegativePcO(Integer rhNegativePcO) {
        this.rhNegativePcO = rhNegativePcO;
    }

    public Integer getRhNegativePaedWbO() {
        return rhNegativePaedWbO;
    }

    public void setRhNegativePaedWbO(Integer rhNegativePaedWbO) {
        this.rhNegativePaedWbO = rhNegativePaedWbO;
    }

    public Integer getRhNegativePaedPcO() {
        return rhNegativePaedPcO;
    }

    public void setRhNegativePaedPcO(Integer rhNegativePaedPcO) {
        this.rhNegativePaedPcO = rhNegativePaedPcO;
    }

    public Integer getTotalO() {
        return totalO;
    }

    public void setTotalO(Integer totalO) {
        this.totalO = totalO;
    }

    public Integer getTotalTransfersToOtherBranches() {
        return totalTransfersToOtherBranches;
    }

    public Integer getCurrentAvailableStock() {
        return currentAvailableStock;
    }

    public void setCurrentAvailableStock(Integer currentAvailableStock) {
        this.currentAvailableStock = currentAvailableStock;
    }

    public void setTotalTransfersToOtherBranches(Integer totalTransfersToOtherBranches) {
        this.totalTransfersToOtherBranches = totalTransfersToOtherBranches;
    }

    public Integer getPercentagefTotalO() {
        return percentagefTotalO;
    }

    public void setPercentagefTotalO(Integer percentagefTotalO) {
        this.percentagefTotalO = percentagefTotalO;
    }

    public Integer getRhPositiveWbA() {
        return rhPositiveWbA;
    }

    public void setRhPositiveWbA(Integer rhPositiveWbA) {
        this.rhPositiveWbA = rhPositiveWbA;
    }

    public Integer getRhPositivePcA() {
        return rhPositivePcA;
    }

    public void setRhPositivePcA(Integer rhPositivePcA) {
        this.rhPositivePcA = rhPositivePcA;
    }

    public Integer getRhPositivePaedWbA() {
        return rhPositivePaedWbA;
    }

    public void setRhPositivePaedWbA(Integer rhPositivePaedWbA) {
        this.rhPositivePaedWbA = rhPositivePaedWbA;
    }

    public Integer getRhPositivePaedPcA() {
        return rhPositivePaedPcA;
    }

    public void setRhPositivePaedPcA(Integer rhPositivePaedPcA) {
        this.rhPositivePaedPcA = rhPositivePaedPcA;
    }

    public Integer getRhNegativeWbA() {
        return rhNegativeWbA;
    }

    public void setRhNegativeWbA(Integer rhNegativeWbA) {
        this.rhNegativeWbA = rhNegativeWbA;
    }

    public Integer getRhNegativePcA() {
        return rhNegativePcA;
    }

    public void setRhNegativePcA(Integer rhNegativePcA) {
        this.rhNegativePcA = rhNegativePcA;
    }

    public Integer getRhNegativePaedWbA() {
        return rhNegativePaedWbA;
    }

    public void setRhNegativePaedWbA(Integer rhNegativePaedWbA) {
        this.rhNegativePaedWbA = rhNegativePaedWbA;
    }

    public Integer getRhNegativePaedPcA() {
        return rhNegativePaedPcA;
    }

    public void setRhNegativePaedPcA(Integer rhNegativePaedPcA) {
        this.rhNegativePaedPcA = rhNegativePaedPcA;
    }

    public Integer getTotalA() {
        return totalA;
    }

    public void setTotalA(Integer totalA) {
        this.totalA = totalA;
    }

    public Integer getPercentageOfTotalA() {
        return percentageOfTotalA;
    }

    public void setPercentageOfTotalA(Integer percentageOfTotalA) {
        this.percentageOfTotalA = percentageOfTotalA;
    }

    public Integer getRhPositiveWbB() {
        return rhPositiveWbB;
    }

    public void setRhPositiveWbB(Integer rhPositiveWbB) {
        this.rhPositiveWbB = rhPositiveWbB;
    }

    public Integer getRhPositivePcB() {
        return rhPositivePcB;
    }

    public void setRhPositivePcB(Integer rhPositivePcB) {
        this.rhPositivePcB = rhPositivePcB;
    }

    public Integer getRhPositivePaedWbB() {
        return rhPositivePaedWbB;
    }

    public void setRhPositivePaedWbB(Integer rhPositivePaedWbB) {
        this.rhPositivePaedWbB = rhPositivePaedWbB;
    }

    public Integer getRhPositivePaedPcB() {
        return rhPositivePaedPcB;
    }

    public void setRhPositivePaedPcB(Integer rhPositivePaedPcB) {
        this.rhPositivePaedPcB = rhPositivePaedPcB;
    }

    public Integer getRhNegativeWbB() {
        return rhNegativeWbB;
    }

    public void setRhNegativeWbB(Integer rhNegativeWbB) {
        this.rhNegativeWbB = rhNegativeWbB;
    }

    public Integer getRhNegativePcB() {
        return rhNegativePcB;
    }

    public void setRhNegativePcB(Integer rhNegativePcB) {
        this.rhNegativePcB = rhNegativePcB;
    }

    public Integer getVerallSupplies() {
        return verallSupplies;
    }

    public void setVerallSupplies(Integer verallSupplies) {
        this.verallSupplies = verallSupplies;
    }

    public Integer getOverallOrders() {
        return overallOrders;
    }

    public void setOverallOrders(Integer overallOrders) {
        this.overallOrders = overallOrders;
    }

    public Integer getOverallDemandVsSupply() {
        return overallDemandVsSupply;
    }

    public void setOverallDemandVsSupply(Integer overallDemandVsSupply) {
        this.overallDemandVsSupply = overallDemandVsSupply;
    }

    public Integer getRhNegativePaedWbB() {
        return rhNegativePaedWbB;
    }

    public void setRhNegativePaedWbB(Integer rhNegativePaedWbB) {
        this.rhNegativePaedWbB = rhNegativePaedWbB;
    }

    public Integer getRhNegativePaedPcB() {
        return rhNegativePaedPcB;
    }

    public void setRhNegativePaedPcB(Integer rhNegativePaedPcB) {
        this.rhNegativePaedPcB = rhNegativePaedPcB;
    }

    public Integer getTotalB() {
        return totalB;
    }

    public void setTotalB(Integer totalB) {
        this.totalB = totalB;
    }

    public Integer getPercentageOfTotalB() {
        return percentageOfTotalB;
    }

    public void setPercentageOfTotalB(Integer percentageOfTotalB) {
        this.percentageOfTotalB = percentageOfTotalB;
    }

    public Integer getRhPositiveWbAB() {
        return rhPositiveWbAB;
    }

    public void setRhPositiveWbAB(Integer rhPositiveWbAB) {
        this.rhPositiveWbAB = rhPositiveWbAB;
    }

    public Integer getRhPositivePcAB() {
        return rhPositivePcAB;
    }

    public void setRhPositivePcAB(Integer rhPositivePcAB) {
        this.rhPositivePcAB = rhPositivePcAB;
    }

    public Integer getRhPositivePaedWbAB() {
        return rhPositivePaedWbAB;
    }

    public void setRhPositivePaedWbAB(Integer rhPositivePaedWbAB) {
        this.rhPositivePaedWbAB = rhPositivePaedWbAB;
    }

    public Integer getRhPositivePaedPcAB() {
        return rhPositivePaedPcAB;
    }

    public void setRhPositivePaedPcAB(Integer rhPositivePaedPcAB) {
        this.rhPositivePaedPcAB = rhPositivePaedPcAB;
    }

    public Integer getRhNegativeWbAB() {
        return rhNegativeWbAB;
    }

    public void setRhNegativeWbAB(Integer rhNegativeWbAB) {
        this.rhNegativeWbAB = rhNegativeWbAB;
    }

    public Integer getRhNegativePcAB() {
        return rhNegativePcAB;
    }

    public void setRhNegativePcAB(Integer rhNegativePcAB) {
        this.rhNegativePcAB = rhNegativePcAB;
    }

    public Integer getRhNegativePaedWbAB() {
        return rhNegativePaedWbAB;
    }

    public void setRhNegativePaedWbAB(Integer rhNegativePaedWbAB) {
        this.rhNegativePaedWbAB = rhNegativePaedWbAB;
    }

    public Integer getRhNegativePaedPcAB() {
        return rhNegativePaedPcAB;
    }

    public void setRhNegativePaedPcAB(Integer rhNegativePaedPcAB) {
        this.rhNegativePaedPcAB = rhNegativePaedPcAB;
    }

    public Integer getTotalAB() {
        return totalAB;
    }

    public void setTotalAB(Integer totalAB) {
        this.totalAB = totalAB;
    }

    public Integer getPercentageOfTotalAB() {
        return percentageOfTotalAB;
    }

    public void setPercentageOfTotalAB(Integer percentageOfTotalAB) {
        this.percentageOfTotalAB = percentageOfTotalAB;
    }

    public Integer getTotalRhPositiveWb() {
        return totalRhPositiveWb;
    }

    public void setTotalRhPositiveWb(Integer totalRhPositiveWb) {
        this.totalRhPositiveWb = totalRhPositiveWb;
    }

    public Integer getTotalRhPositivePc() {
        return totalRhPositivePc;
    }

    public void setTotalRhPositivePc(Integer totalRhPositivePc) {
        this.totalRhPositivePc = totalRhPositivePc;
    }

    public Integer getTotalRhPositivePaedWb() {
        return totalRhPositivePaedWb;
    }

    public void setTotalRhPositivePaedWb(Integer totalRhPositivePaedWb) {
        this.totalRhPositivePaedWb = totalRhPositivePaedWb;
    }

    public Integer getTotalRhPositivePaedPc() {
        return totalRhPositivePaedPc;
    }

    public void setTotalRhPositivePaedPc(Integer totalRhPositivePaedPc) {
        this.totalRhPositivePaedPc = totalRhPositivePaedPc;
    }

    public Integer getTotalRhNegativeWb() {
        return totalRhNegativeWb;
    }

    public void setTotalRhNegativeWb(Integer totalRhNegativeWb) {
        this.totalRhNegativeWb = totalRhNegativeWb;
    }

    public Integer getTotalRhNegativePc() {
        return totalRhNegativePc;
    }

    public void setTotalRhNegativePc(Integer totalRhNegativePc) {
        this.totalRhNegativePc = totalRhNegativePc;
    }

    public Integer getTotalRhNegativePaedWb() {
        return totalRhNegativePaedWb;
    }

    public void setTotalRhNegativePaedWb(Integer totalRhNegativePaedWb) {
        this.totalRhNegativePaedWb = totalRhNegativePaedWb;
    }

    public Integer getTotalRhNegativePaedPc() {
        return totalRhNegativePaedPc;
    }

    public void setTotalRhNegativePaedPc(Integer totalRhNegativePaedPc) {
        this.totalRhNegativePaedPc = totalRhNegativePaedPc;
    }

    public Integer getTotalTotal() {
        return totalTotal;
    }

    public void setTotalTotal(Integer totalTotal) {
        this.totalTotal = totalTotal;
    }

    public Integer getTotalPercentageOfTotal() {
        return totalPercentageOfTotal;
    }

    public void setTotalPercentageOfTotal(Integer totalPercentageOfTotal) {
        this.totalPercentageOfTotal = totalPercentageOfTotal;
    }

    public Integer getRhPositiveWbOcompatibility() {
        return rhPositiveWbOcompatibility;
    }

    public void setRhPositiveWbOcompatibility(Integer rhPositiveWbOcompatibility) {
        this.rhPositiveWbOcompatibility = rhPositiveWbOcompatibility;
    }

    public Integer getRhPositivePcOcompatibility() {
        return rhPositivePcOcompatibility;
    }

    public void setRhPositivePcOcompatibility(Integer rhPositivePcOcompatibility) {
        this.rhPositivePcOcompatibility = rhPositivePcOcompatibility;
    }

    public Integer getRhPositivePaedWbOcompatibility() {
        return rhPositivePaedWbOcompatibility;
    }

    public void setRhPositivePaedWbOcompatibility(Integer rhPositivePaedWbOcompatibility) {
        this.rhPositivePaedWbOcompatibility = rhPositivePaedWbOcompatibility;
    }

    public Integer getRhPositivePaedPcOcompatibility() {
        return rhPositivePaedPcOcompatibility;
    }

    public void setRhPositivePaedPcOcompatibility(Integer rhPositivePaedPcOcompatibility) {
        this.rhPositivePaedPcOcompatibility = rhPositivePaedPcOcompatibility;
    }

    public Integer getRhNegativeWbOcompatibility() {
        return rhNegativeWbOcompatibility;
    }

    public void setRhNegativeWbOcompatibility(Integer rhNegativeWbOcompatibility) {
        this.rhNegativeWbOcompatibility = rhNegativeWbOcompatibility;
    }

    public Integer getRhNegativePcOcompatibility() {
        return rhNegativePcOcompatibility;
    }

    public void setRhNegativePcOcompatibility(Integer rhNegativePcOcompatibility) {
        this.rhNegativePcOcompatibility = rhNegativePcOcompatibility;
    }

    public Integer getRhNegativePaedWbOcompatibility() {
        return rhNegativePaedWbOcompatibility;
    }

    public void setRhNegativePaedWbOcompatibility(Integer rhNegativePaedWbOcompatibility) {
        this.rhNegativePaedWbOcompatibility = rhNegativePaedWbOcompatibility;
    }

    public Integer getRhNegativePaedPcOcompatibility() {
        return rhNegativePaedPcOcompatibility;
    }

    public void setRhNegativePaedPcOcompatibility(Integer rhNegativePaedPcOcompatibility) {
        this.rhNegativePaedPcOcompatibility = rhNegativePaedPcOcompatibility;
    }

    public Integer getTotalOcompatibility() {
        return totalOcompatibility;
    }

    public void setTotalOcompatibility(Integer totalOcompatibility) {
        this.totalOcompatibility = totalOcompatibility;
    }

    public Integer getPercentageOfTotalOcompatibility() {
        return percentageOfTotalOcompatibility;
    }

    public void setPercentageOfTotalOcompatibility(Integer percentageOfTotalOcompatibility) {
        this.percentageOfTotalOcompatibility = percentageOfTotalOcompatibility;
    }

    public Integer getRhPositiveWbAcompatibility() {
        return rhPositiveWbAcompatibility;
    }

    public void setRhPositiveWbAcompatibility(Integer rhPositiveWbAcompatibility) {
        this.rhPositiveWbAcompatibility = rhPositiveWbAcompatibility;
    }

    public Integer getRhPositivePcAcompatibility() {
        return rhPositivePcAcompatibility;
    }

    public void setRhPositivePcAcompatibility(Integer rhPositivePcAcompatibility) {
        this.rhPositivePcAcompatibility = rhPositivePcAcompatibility;
    }

    public Integer getRhPositivePaedWbAcompatibility() {
        return rhPositivePaedWbAcompatibility;
    }

    public void setRhPositivePaedWbAcompatibility(Integer rhPositivePaedWbAcompatibility) {
        this.rhPositivePaedWbAcompatibility = rhPositivePaedWbAcompatibility;
    }

    public Integer getRhPositivePaedPcAcompatibility() {
        return rhPositivePaedPcAcompatibility;
    }

    public void setRhPositivePaedPcAcompatibility(Integer rhPositivePaedPcAcompatibility) {
        this.rhPositivePaedPcAcompatibility = rhPositivePaedPcAcompatibility;
    }

    public Integer getRhNegativeWbAcompatibility() {
        return rhNegativeWbAcompatibility;
    }

    public void setRhNegativeWbAcompatibility(Integer rhNegativeWbAcompatibility) {
        this.rhNegativeWbAcompatibility = rhNegativeWbAcompatibility;
    }

    public Integer getRhNegativePcAcompatibility() {
        return rhNegativePcAcompatibility;
    }

    public void setRhNegativePcAcompatibility(Integer rhNegativePcAcompatibility) {
        this.rhNegativePcAcompatibility = rhNegativePcAcompatibility;
    }

    public Integer getRhNegativePaedWbAcompatibility() {
        return rhNegativePaedWbAcompatibility;
    }

    public void setRhNegativePaedWbAcompatibility(Integer rhNegativePaedWbAcompatibility) {
        this.rhNegativePaedWbAcompatibility = rhNegativePaedWbAcompatibility;
    }

    public Integer getRhNegativePaedPcAcompatibility() {
        return rhNegativePaedPcAcompatibility;
    }

    public void setRhNegativePaedPcAcompatibility(Integer rhNegativePaedPcAcompatibility) {
        this.rhNegativePaedPcAcompatibility = rhNegativePaedPcAcompatibility;
    }

    public Integer getTotalAcompatibility() {
        return totalAcompatibility;
    }

    public void setTotalAcompatibility(Integer totalAcompatibility) {
        this.totalAcompatibility = totalAcompatibility;
    }

    public Integer getPercentageOfTotalAcompatibility() {
        return percentageOfTotalAcompatibility;
    }

    public void setPercentageOfTotalAcompatibility(Integer percentageOfTotalAcompatibility) {
        this.percentageOfTotalAcompatibility = percentageOfTotalAcompatibility;
    }

    public Integer getRhPositiveWbBcompatibility() {
        return rhPositiveWbBcompatibility;
    }

    public void setRhPositiveWbBcompatibility(Integer rhPositiveWbBcompatibility) {
        this.rhPositiveWbBcompatibility = rhPositiveWbBcompatibility;
    }

    public Integer getRhPositivePcBcompatibility() {
        return rhPositivePcBcompatibility;
    }

    public void setRhPositivePcBcompatibility(Integer rhPositivePcBcompatibility) {
        this.rhPositivePcBcompatibility = rhPositivePcBcompatibility;
    }

    public Integer getRhPositivePaedWbBcompatibility() {
        return rhPositivePaedWbBcompatibility;
    }

    public void setRhPositivePaedWbBcompatibility(Integer rhPositivePaedWbBcompatibility) {
        this.rhPositivePaedWbBcompatibility = rhPositivePaedWbBcompatibility;
    }

    public Integer getRhPositivePaedPcBcompatibility() {
        return rhPositivePaedPcBcompatibility;
    }

    public void setRhPositivePaedPcBcompatibility(Integer rhPositivePaedPcBcompatibility) {
        this.rhPositivePaedPcBcompatibility = rhPositivePaedPcBcompatibility;
    }

    public Integer getRhNegativeWbBcompatibility() {
        return rhNegativeWbBcompatibility;
    }

    public void setRhNegativeWbBcompatibility(Integer rhNegativeWbBcompatibility) {
        this.rhNegativeWbBcompatibility = rhNegativeWbBcompatibility;
    }

    public Integer getRhNegativePcBcompatibility() {
        return rhNegativePcBcompatibility;
    }

    public void setRhNegativePcBcompatibility(Integer rhNegativePcBcompatibility) {
        this.rhNegativePcBcompatibility = rhNegativePcBcompatibility;
    }

    public Integer getRhNegativePaedWbBcompatibility() {
        return rhNegativePaedWbBcompatibility;
    }

    public void setRhNegativePaedWbBcompatibility(Integer rhNegativePaedWbBcompatibility) {
        this.rhNegativePaedWbBcompatibility = rhNegativePaedWbBcompatibility;
    }

    public Integer getRhNegativePaedPcBcompatibility() {
        return rhNegativePaedPcBcompatibility;
    }

    public void setRhNegativePaedPcBcompatibility(Integer rhNegativePaedPcBcompatibility) {
        this.rhNegativePaedPcBcompatibility = rhNegativePaedPcBcompatibility;
    }

    public Integer getTotalBcompatibility() {
        return totalBcompatibility;
    }

    public void setTotalBcompatibility(Integer totalBcompatibility) {
        this.totalBcompatibility = totalBcompatibility;
    }

    public Integer getPercentageOfTotalBcompatibility() {
        return percentageOfTotalBcompatibility;
    }

    public void setPercentageOfTotalBcompatibility(Integer percentageOfTotalBcompatibility) {
        this.percentageOfTotalBcompatibility = percentageOfTotalBcompatibility;
    }

    public Integer getRhPositiveWbABcompatibility() {
        return rhPositiveWbABcompatibility;
    }

    public void setRhPositiveWbABcompatibility(Integer rhPositiveWbABcompatibility) {
        this.rhPositiveWbABcompatibility = rhPositiveWbABcompatibility;
    }

    public Integer getRhPositivePcABcompatibility() {
        return rhPositivePcABcompatibility;
    }

    public void setRhPositivePcABcompatibility(Integer rhPositivePcABcompatibility) {
        this.rhPositivePcABcompatibility = rhPositivePcABcompatibility;
    }

    public Integer getRhPositivePaedWbABcompatibility() {
        return rhPositivePaedWbABcompatibility;
    }

    public void setRhPositivePaedWbABcompatibility(Integer rhPositivePaedWbABcompatibility) {
        this.rhPositivePaedWbABcompatibility = rhPositivePaedWbABcompatibility;
    }

    public Integer getRhPositivePaedPcABcompatibility() {
        return rhPositivePaedPcABcompatibility;
    }

    public void setRhPositivePaedPcABcompatibility(Integer rhPositivePaedPcABcompatibility) {
        this.rhPositivePaedPcABcompatibility = rhPositivePaedPcABcompatibility;
    }

    public Integer getRhNegativeWbABcompatibility() {
        return rhNegativeWbABcompatibility;
    }

    public void setRhNegativeWbABcompatibility(Integer rhNegativeWbABcompatibility) {
        this.rhNegativeWbABcompatibility = rhNegativeWbABcompatibility;
    }

    public Integer getRhNegativePcABcompatibility() {
        return rhNegativePcABcompatibility;
    }

    public void setRhNegativePcABcompatibility(Integer rhNegativePcABcompatibility) {
        this.rhNegativePcABcompatibility = rhNegativePcABcompatibility;
    }

    public Integer getRhNegativePaedWbABcompatibility() {
        return rhNegativePaedWbABcompatibility;
    }

    public void setRhNegativePaedWbABcompatibility(Integer rhNegativePaedWbABcompatibility) {
        this.rhNegativePaedWbABcompatibility = rhNegativePaedWbABcompatibility;
    }

    public Integer getRhNegativePaedPcABcompatibility() {
        return rhNegativePaedPcABcompatibility;
    }

    public void setRhNegativePaedPcABcompatibility(Integer rhNegativePaedPcABcompatibility) {
        this.rhNegativePaedPcABcompatibility = rhNegativePaedPcABcompatibility;
    }

    public Integer getTotalABcompatibility() {
        return totalABcompatibility;
    }

    public void setTotalABcompatibility(Integer totalABcompatibility) {
        this.totalABcompatibility = totalABcompatibility;
    }

    public Integer getPercentageOfTotalABcompatibility() {
        return percentageOfTotalABcompatibility;
    }

    public void setPercentageOfTotalABcompatibility(Integer percentageOfTotalABcompatibility) {
        this.percentageOfTotalABcompatibility = percentageOfTotalABcompatibility;
    }

    public Integer getTotalRhPositiveWbcompatibility() {
        return totalRhPositiveWbcompatibility;
    }

    public void setTotalRhPositiveWbcompatibility(Integer totalRhPositiveWbcompatibility) {
        this.totalRhPositiveWbcompatibility = totalRhPositiveWbcompatibility;
    }

    public Integer getTotalRhPositivePccompatibility() {
        return totalRhPositivePccompatibility;
    }

    public void setTotalRhPositivePccompatibility(Integer totalRhPositivePccompatibility) {
        this.totalRhPositivePccompatibility = totalRhPositivePccompatibility;
    }

    public Integer getTotalRhPositivePaedWbcompatibility() {
        return totalRhPositivePaedWbcompatibility;
    }

    public void setTotalRhPositivePaedWbcompatibility(Integer totalRhPositivePaedWbcompatibility) {
        this.totalRhPositivePaedWbcompatibility = totalRhPositivePaedWbcompatibility;
    }

    public Integer getTotalRhPositivePaedPccompatibility() {
        return totalRhPositivePaedPccompatibility;
    }

    public void setTotalRhPositivePaedPccompatibility(Integer totalRhPositivePaedPccompatibility) {
        this.totalRhPositivePaedPccompatibility = totalRhPositivePaedPccompatibility;
    }

    public Integer getTotalRhNegativeWbcompatibility() {
        return totalRhNegativeWbcompatibility;
    }

    public void setTotalRhNegativeWbcompatibility(Integer totalRhNegativeWbcompatibility) {
        this.totalRhNegativeWbcompatibility = totalRhNegativeWbcompatibility;
    }

    public Integer getTotalRhNegativePccompatibility() {
        return totalRhNegativePccompatibility;
    }

    public void setTotalRhNegativePccompatibility(Integer totalRhNegativePccompatibility) {
        this.totalRhNegativePccompatibility = totalRhNegativePccompatibility;
    }

    public Integer getTotalRhNegativePaedWbcompatibility() {
        return totalRhNegativePaedWbcompatibility;
    }

    public void setTotalRhNegativePaedWbcompatibility(Integer totalRhNegativePaedWbcompatibility) {
        this.totalRhNegativePaedWbcompatibility = totalRhNegativePaedWbcompatibility;
    }

    public Integer getTotalRhNegativePaedPccompatibility() {
        return totalRhNegativePaedPccompatibility;
    }

    public void setTotalRhNegativePaedPccompatibility(Integer totalRhNegativePaedPccompatibility) {
        this.totalRhNegativePaedPccompatibility = totalRhNegativePaedPccompatibility;
    }

    public Integer getTotalTotalcompatibility() {
        return totalTotalcompatibility;
    }

    public void setTotalTotalcompatibility(Integer totalTotalcompatibility) {
        this.totalTotalcompatibility = totalTotalcompatibility;
    }

    public Integer getTotalPercentageOfTotalcompatibility() {
        return totalPercentageOfTotalcompatibility;
    }

    public void setTotalPercentageOfTotalcompatibility(Integer totalPercentageOfTotalcompatibility) {
        this.totalPercentageOfTotalcompatibility = totalPercentageOfTotalcompatibility;
    }

    public Integer getCompatsIssues() {
        return compatsIssues;
    }

    public void setCompatsIssues(Integer compatsIssues) {
        this.compatsIssues = compatsIssues;
    }

    public Integer getCompatsOrders() {
        return compatsOrders;
    }

    public void setCompatsOrders(Integer compatsOrders) {
        this.compatsOrders = compatsOrders;
    }

    public Integer getCompatsPercentageSupply_Orders() {
        return compatsPercentageSupply_Orders;
    }

    public void setCompatsPercentageSupply_Orders(Integer compatsPercentageSupply_Orders) {
        this.compatsPercentageSupply_Orders = compatsPercentageSupply_Orders;
    }

    public Integer getFfp1() {
        return ffp1;
    }

    public void setFfp1(Integer ffp1) {
        this.ffp1 = ffp1;
    }

    public Integer getPlt1() {
        return plt1;
    }

    public void setPlt1(Integer plt1) {
        this.plt1 = plt1;
    }

    public Integer getPlt2() {
        return plt2;
    }

    public void setPlt2(Integer plt2) {
        this.plt2 = plt2;
    }

    public Integer getCryo() {
        return cryo;
    }

    public void setCryo(Integer cryo) {
        this.cryo = cryo;
    }

    public Integer getPaedPacks() {
        return paedPacks;
    }

    public Integer getTotalReceipts() {
        return totalReceipts;
    }

    public void setTotalReceipts(Integer totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    public void setPaedPacks(Integer paedPacks) {
        this.paedPacks = paedPacks;
    }

    public Set<StockReceivedFromAvailable> getStockReceivedFromAvailable() {
        return stockReceivedFromAvailable;
    }

    public void setStockReceivedFromAvailable(Set<StockReceivedFromAvailable> stockReceivedFromAvailable) {
        this.stockReceivedFromAvailable = stockReceivedFromAvailable;
    }

    public Set<StockIssuedToAvailable> getStockIssuedToAvailable() {
        return stockIssuedToAvailable;
    }

    public void setStockIssuedToAvailable(Set<StockIssuedToAvailable> stockIssuedToAvailable) {
        this.stockIssuedToAvailable = stockIssuedToAvailable;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<StockReceivedFromAvailable> getReceivedFromAvailable() {
        return receivedFromAvailable;
    }

    public void setReceivedFromAvailable(List<StockReceivedFromAvailable> receivedFromAvailable) {
        this.receivedFromAvailable = receivedFromAvailable;
    }

    public List<StockIssuedToAvailable> getIssuedToAvailable() {
        return issuedToAvailable;
    }

    public void setIssuedToAvailable(List<StockIssuedToAvailable> issuedToAvailable) {
        this.issuedToAvailable = issuedToAvailable;
    }
}
