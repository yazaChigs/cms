package com.totalit.bloodbankstatement.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class StockQuarantined extends BaseEntity {

    @Temporal(TemporalType.DATE)
    private Date todaysDate;
    private Integer openingStock;
    private Integer harareCbd03;
    private Integer staticHq01;
    private Integer mobile04;
    private Integer mobile02;
    private Integer mobile06;
    private Integer totalCollections;
    private Integer referenceLaboratory;
    private Integer totalReceiptsFromBranches;
    private Integer p1;
    private Integer dryPacksD3D4;
    private Integer p2;
    private Integer dryPacksD1;
    private Integer p3;
    private Integer samplesOnly;
    private Integer c11;
    private Integer expired;
    private Integer wrongPack;
    private Integer broken;
    private Integer other;
    private Integer serologicalDiscards;
    private Integer totalIssuesDiscards;
    private Integer availableStock;
    private Integer issueTogroupMismatchesToRefLab;
    private Integer totalIssues;
    private Integer ffp1;
    private Integer plt1;
    private Integer plt2;
    private Integer cryo;


    @JsonIgnore
    @OneToMany( mappedBy = "stockQuarantined", fetch = FetchType.EAGER)
    private Set<StockIssuedToQuarantine> stockIssuedToQuarantines;

    @Transient
    private  List<StockIssuedToQuarantine> issuedToQuarantines;

    @JsonIgnore
    @OneToMany( mappedBy = "stockQuarantined", fetch = FetchType.EAGER)
    private Set<StockReceivedFromQuarantined> stockReceivedFromQuarantineds;

    @Transient
    private List<StockReceivedFromQuarantined> receivedFromQuarantineds;

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

    public Integer getHarareCbd03() {
        return harareCbd03;
    }

    public void setHarareCbd03(Integer harareCbd03) {
        this.harareCbd03 = harareCbd03;
    }

    public Integer getStaticHq01() {
        return staticHq01;
    }

    public void setStaticHq01(Integer staticHq01) {
        this.staticHq01 = staticHq01;
    }

    public Integer getMobile04() {
        return mobile04;
    }

    public void setMobile04(Integer mobile04) {
        this.mobile04 = mobile04;
    }

    public Integer getMobile02() {
        return mobile02;
    }

    public void setMobile02(Integer mobile02) {
        this.mobile02 = mobile02;
    }

    public Integer getMobile06() {
        return mobile06;
    }

    public void setMobile06(Integer mobile06) {
        this.mobile06 = mobile06;
    }

    public Integer getTotalCollections() {
        return totalCollections;
    }

    public void setTotalCollections(Integer totalCollections) {
        this.totalCollections = totalCollections;
    }

    public Integer getReferenceLaboratory() {
        return referenceLaboratory;
    }

    public void setReferenceLaboratory(Integer referenceLaboratory) {
        this.referenceLaboratory = referenceLaboratory;
    }

    public Integer getTotalReceiptsFromBranches() {
        return totalReceiptsFromBranches;
    }

    public void setTotalReceiptsFromBranches(Integer totalReceiptsFromBranches) {
        this.totalReceiptsFromBranches = totalReceiptsFromBranches;
    }

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public Integer getDryPacksD3D4() {
        return dryPacksD3D4;
    }

    public void setDryPacksD3D4(Integer dryPacksD3D4) {
        this.dryPacksD3D4 = dryPacksD3D4;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public Integer getDryPacksD1() {
        return dryPacksD1;
    }

    public void setDryPacksD1(Integer dryPacksD1) {
        this.dryPacksD1 = dryPacksD1;
    }

    public Integer getP3() {
        return p3;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }

    public Integer getSamplesOnly() {
        return samplesOnly;
    }

    public void setSamplesOnly(Integer samplesOnly) {
        this.samplesOnly = samplesOnly;
    }

    public Integer getC11() {
        return c11;
    }

    public void setC11(Integer c11) {
        this.c11 = c11;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getWrongPack() {
        return wrongPack;
    }

    public void setWrongPack(Integer wrongPack) {
        this.wrongPack = wrongPack;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getSerologicalDiscards() {
        return serologicalDiscards;
    }

    public void setSerologicalDiscards(Integer serologicalDiscards) {
        this.serologicalDiscards = serologicalDiscards;
    }

    public Integer getTotalIssuesDiscards() {
        return totalIssuesDiscards;
    }

    public void setTotalIssuesDiscards(Integer totalIssuesDiscards) {
        this.totalIssuesDiscards = totalIssuesDiscards;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public Integer getIssueTogroupMismatchesToRefLab() {
        return issueTogroupMismatchesToRefLab;
    }

    public void setIssueTogroupMismatchesToRefLab(Integer issueTogroupMismatchesToRefLab) {
        this.issueTogroupMismatchesToRefLab = issueTogroupMismatchesToRefLab;
    }

    public Integer getTotalIssues() {
        return totalIssues;
    }

    public void setTotalIssues(Integer totalIssues) {
        this.totalIssues = totalIssues;
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

    public Set<StockIssuedToQuarantine> getStockIssuedToQuarantines() {
        return stockIssuedToQuarantines;
    }

    public void setStockIssuedToQuarantines(Set<StockIssuedToQuarantine> stockIssuedToQuarantines) {
        this.stockIssuedToQuarantines = stockIssuedToQuarantines;
    }

    public Set<StockReceivedFromQuarantined> getStockReceivedFromQuarantineds() {
        return stockReceivedFromQuarantineds;
    }

    public void setStockReceivedFromQuarantineds(Set<StockReceivedFromQuarantined> stockReceivedFromQuarantineds) {
        this.stockReceivedFromQuarantineds = stockReceivedFromQuarantineds;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<StockIssuedToQuarantine> getIssuedToQuarantines() {
        return issuedToQuarantines;
    }

    public void setIssuedToQuarantines(List<StockIssuedToQuarantine> issuedToQuarantines) {
        this.issuedToQuarantines = issuedToQuarantines;
    }

    public List<StockReceivedFromQuarantined> getReceivedFromQuarantineds() {
        return receivedFromQuarantineds;
    }

    public void setReceivedFromQuarantineds(List<StockReceivedFromQuarantined> receivedFromQuarantineds) {
        this.receivedFromQuarantineds = receivedFromQuarantineds;
    }

    public Integer getBroken() {
        return broken;
    }

    public void setBroken(Integer broken) {
        this.broken = broken;
    }
}