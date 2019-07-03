package com.totalit.bloodbankstatement.domain.config.Admin;

import com.totalit.bloodbankstatement.domain.config.BaseEntity;
import com.totalit.bloodbankstatement.domain.config.Branch;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UnadjustedDailyRequirements extends BaseEntity {

    private Integer oPlusHarare;
    private Integer oMinusHarare;
    private Integer aPlusHarare;
    private Integer aMinusHarare;
    private Integer bPlusHarare;
    private Integer bMinusHarare;
    private Integer abPlusHarare;
    private Integer abMinusHarare;
    private Integer totalHarare;
    private Integer oPlusBulawayo;
    private Integer oMinusBulawayo;
    private Integer aPlusBulawayo;
    private Integer aMinusBulawayo;
    private Integer bPlusBulawayo;
    private Integer bMinusBulawayo;
    private Integer abPlusBulawayo;
    private Integer abMinusBulawayo;
    private Integer totalBulawayo;
    private Integer oPlusGweru;
    private Integer oMinusGweru;
    private Integer aPlusGweru;
    private Integer aMinusGweru;
    private Integer bPlusGweru;
    private Integer bMinusGweru;
    private Integer abPlusGweru;
    private Integer abMinusGweru;
    private Integer totalGweru;
    private Integer oPlusMutare;
    private Integer oMinusMutare;
    private Integer aPlusMutare;
    private Integer aMinusMutare;
    private Integer bPlusMutare;
    private Integer bMinusMutare;
    private Integer abPlusMutare;
    private Integer abMinusMutare;
    private Integer totalMutare;
    private Integer oPlusMasvingo;
    private Integer oMinusMasvingo;
    private Integer aPlusMasvingo;
    private Integer aMinusMasvingo;
    private Integer bPlusMasvingo;
    private Integer bMinusMasvingo;
    private Integer abPlusMasvingo;
    private Integer abMinusMasvingo;
    private Integer totalMasvingo;
    private Integer oPlusTotalDailyRequirements;
    private Integer oMinusTotalDailyRequirements;
    private Integer aPlusTotalDailyRequirements;
    private Integer aMinusTotalDailyRequirements;
    private Integer bPlusTotalDailyRequirements;
    private Integer bMinusTotalDailyRequirements;
    private Integer abPlusTotalDailyRequirements;
    private Integer abMinusTotalDailyRequirements;
    private Integer totalTotalDailyRequirements;

    @ManyToOne
    private Branch branch;

    public Integer getoPlusHarare() {
        return oPlusHarare;
    }

    public void setoPlusHarare(Integer oPlusHarare) {
        this.oPlusHarare = oPlusHarare;
    }

    public Integer getoMinusHarare() {
        return oMinusHarare;
    }

    public void setoMinusHarare(Integer oMinusHarare) {
        this.oMinusHarare = oMinusHarare;
    }

    public Integer getaPlusHarare() {
        return aPlusHarare;
    }

    public void setaPlusHarare(Integer aPlusHarare) {
        this.aPlusHarare = aPlusHarare;
    }

    public Integer getaMinusHarare() {
        return aMinusHarare;
    }

    public void setaMinusHarare(Integer aMinusHarare) {
        this.aMinusHarare = aMinusHarare;
    }

    public Integer getbPlusHarare() {
        return bPlusHarare;
    }

    public void setbPlusHarare(Integer bPlusHarare) {
        this.bPlusHarare = bPlusHarare;
    }

    public Integer getbMinusHarare() {
        return bMinusHarare;
    }

    public void setbMinusHarare(Integer bMinusHarare) {
        this.bMinusHarare = bMinusHarare;
    }

    public Integer getAbPlusHarare() {
        return abPlusHarare;
    }

    public void setAbPlusHarare(Integer abPlusHarare) {
        this.abPlusHarare = abPlusHarare;
    }

    public Integer getAbMinusHarare() {
        return abMinusHarare;
    }

    public void setAbMinusHarare(Integer abMinusHarare) {
        this.abMinusHarare = abMinusHarare;
    }

    public Integer getTotalHarare() {
        return totalHarare;
    }

    public void setTotalHarare(Integer totalHarare) {
        this.totalHarare = totalHarare;
    }

    public Integer getoPlusBulawayo() {
        return oPlusBulawayo;
    }

    public void setoPlusBulawayo(Integer oPlusBulawayo) {
        this.oPlusBulawayo = oPlusBulawayo;
    }

    public Integer getoMinusBulawayo() {
        return oMinusBulawayo;
    }

    public void setoMinusBulawayo(Integer oMinusBulawayo) {
        this.oMinusBulawayo = oMinusBulawayo;
    }

    public Integer getaPlusBulawayo() {
        return aPlusBulawayo;
    }

    public void setaPlusBulawayo(Integer aPlusBulawayo) {
        this.aPlusBulawayo = aPlusBulawayo;
    }

    public Integer getaMinusBulawayo() {
        return aMinusBulawayo;
    }

    public void setaMinusBulawayo(Integer aMinusBulawayo) {
        this.aMinusBulawayo = aMinusBulawayo;
    }

    public Integer getbPlusBulawayo() {
        return bPlusBulawayo;
    }

    public void setbPlusBulawayo(Integer bPlusBulawayo) {
        this.bPlusBulawayo = bPlusBulawayo;
    }

    public Integer getbMinusBulawayo() {
        return bMinusBulawayo;
    }

    public void setbMinusBulawayo(Integer bMinusBulawayo) {
        this.bMinusBulawayo = bMinusBulawayo;
    }

    public Integer getAbPlusBulawayo() {
        return abPlusBulawayo;
    }

    public void setAbPlusBulawayo(Integer abPlusBulawayo) {
        this.abPlusBulawayo = abPlusBulawayo;
    }

    public Integer getAbMinusBulawayo() {
        return abMinusBulawayo;
    }

    public void setAbMinusBulawayo(Integer abMinusBulawayo) {
        this.abMinusBulawayo = abMinusBulawayo;
    }

    public Integer getTotalBulawayo() {
        return totalBulawayo;
    }

    public void setTotalBulawayo(Integer totalBulawayo) {
        this.totalBulawayo = totalBulawayo;
    }

    public Integer getoPlusGweru() {
        return oPlusGweru;
    }

    public void setoPlusGweru(Integer oPlusGweru) {
        this.oPlusGweru = oPlusGweru;
    }

    public Integer getoMinusGweru() {
        return oMinusGweru;
    }

    public void setoMinusGweru(Integer oMinusGweru) {
        this.oMinusGweru = oMinusGweru;
    }

    public Integer getaPlusGweru() {
        return aPlusGweru;
    }

    public void setaPlusGweru(Integer aPlusGweru) {
        this.aPlusGweru = aPlusGweru;
    }

    public Integer getaMinusGweru() {
        return aMinusGweru;
    }

    public void setaMinusGweru(Integer aMinusGweru) {
        this.aMinusGweru = aMinusGweru;
    }

    public Integer getbPlusGweru() {
        return bPlusGweru;
    }

    public void setbPlusGweru(Integer bPlusGweru) {
        this.bPlusGweru = bPlusGweru;
    }

    public Integer getbMinusGweru() {
        return bMinusGweru;
    }

    public void setbMinusGweru(Integer bMinusGweru) {
        this.bMinusGweru = bMinusGweru;
    }

    public Integer getAbPlusGweru() {
        return abPlusGweru;
    }

    public void setAbPlusGweru(Integer abPlusGweru) {
        this.abPlusGweru = abPlusGweru;
    }

    public Integer getAbMinusGweru() {
        return abMinusGweru;
    }

    public void setAbMinusGweru(Integer abMinusGweru) {
        this.abMinusGweru = abMinusGweru;
    }

    public Integer getTotalGweru() {
        return totalGweru;
    }

    public void setTotalGweru(Integer totalGweru) {
        this.totalGweru = totalGweru;
    }

    public Integer getoPlusMutare() {
        return oPlusMutare;
    }

    public void setoPlusMutare(Integer oPlusMutare) {
        this.oPlusMutare = oPlusMutare;
    }

    public Integer getoMinusMutare() {
        return oMinusMutare;
    }

    public void setoMinusMutare(Integer oMinusMutare) {
        this.oMinusMutare = oMinusMutare;
    }

    public Integer getaPlusMutare() {
        return aPlusMutare;
    }

    public void setaPlusMutare(Integer aPlusMutare) {
        this.aPlusMutare = aPlusMutare;
    }

    public Integer getaMinusMutare() {
        return aMinusMutare;
    }

    public void setaMinusMutare(Integer aMinusMutare) {
        this.aMinusMutare = aMinusMutare;
    }

    public Integer getbPlusMutare() {
        return bPlusMutare;
    }

    public void setbPlusMutare(Integer bPlusMutare) {
        this.bPlusMutare = bPlusMutare;
    }

    public Integer getbMinusMutare() {
        return bMinusMutare;
    }

    public void setbMinusMutare(Integer bMinusMutare) {
        this.bMinusMutare = bMinusMutare;
    }

    public Integer getAbPlusMutare() {
        return abPlusMutare;
    }

    public void setAbPlusMutare(Integer abPlusMutare) {
        this.abPlusMutare = abPlusMutare;
    }

    public Integer getAbMinusMutare() {
        return abMinusMutare;
    }

    public void setAbMinusMutare(Integer abMinusMutare) {
        this.abMinusMutare = abMinusMutare;
    }

    public Integer getTotalMutare() {
        return totalMutare;
    }

    public void setTotalMutare(Integer totalMutare) {
        this.totalMutare = totalMutare;
    }

    public Integer getoPlusMasvingo() {
        return oPlusMasvingo;
    }

    public void setoPlusMasvingo(Integer oPlusMasvingo) {
        this.oPlusMasvingo = oPlusMasvingo;
    }

    public Integer getoMinusMasvingo() {
        return oMinusMasvingo;
    }

    public void setoMinusMasvingo(Integer oMinusMasvingo) {
        this.oMinusMasvingo = oMinusMasvingo;
    }

    public Integer getaPlusMasvingo() {
        return aPlusMasvingo;
    }

    public void setaPlusMasvingo(Integer aPlusMasvingo) {
        this.aPlusMasvingo = aPlusMasvingo;
    }

    public Integer getaMinusMasvingo() {
        return aMinusMasvingo;
    }

    public void setaMinusMasvingo(Integer aMinusMasvingo) {
        this.aMinusMasvingo = aMinusMasvingo;
    }

    public Integer getbPlusMasvingo() {
        return bPlusMasvingo;
    }

    public void setbPlusMasvingo(Integer bPlusMasvingo) {
        this.bPlusMasvingo = bPlusMasvingo;
    }

    public Integer getbMinusMasvingo() {
        return bMinusMasvingo;
    }

    public void setbMinusMasvingo(Integer bMinusMasvingo) {
        this.bMinusMasvingo = bMinusMasvingo;
    }

    public Integer getAbPlusMasvingo() {
        return abPlusMasvingo;
    }

    public void setAbPlusMasvingo(Integer abPlusMasvingo) {
        this.abPlusMasvingo = abPlusMasvingo;
    }

    public Integer getAbMinusMasvingo() {
        return abMinusMasvingo;
    }

    public void setAbMinusMasvingo(Integer abMinusMasvingo) {
        this.abMinusMasvingo = abMinusMasvingo;
    }

    public Integer getTotalMasvingo() {
        return totalMasvingo;
    }

    public void setTotalMasvingo(Integer totalMasvingo) {
        this.totalMasvingo = totalMasvingo;
    }

    public Integer getoPlusTotalDailyRequirements() {
        return oPlusTotalDailyRequirements;
    }

    public void setoPlusTotalDailyRequirements(Integer oPlusTotalDailyRequirements) {
        this.oPlusTotalDailyRequirements = oPlusTotalDailyRequirements;
    }

    public Integer getoMinusTotalDailyRequirements() {
        return oMinusTotalDailyRequirements;
    }

    public void setoMinusTotalDailyRequirements(Integer oMinusTotalDailyRequirements) {
        this.oMinusTotalDailyRequirements = oMinusTotalDailyRequirements;
    }

    public Integer getaPlusTotalDailyRequirements() {
        return aPlusTotalDailyRequirements;
    }

    public void setaPlusTotalDailyRequirements(Integer aPlusTotalDailyRequirements) {
        this.aPlusTotalDailyRequirements = aPlusTotalDailyRequirements;
    }

    public Integer getaMinusTotalDailyRequirements() {
        return aMinusTotalDailyRequirements;
    }

    public void setaMinusTotalDailyRequirements(Integer aMinusTotalDailyRequirements) {
        this.aMinusTotalDailyRequirements = aMinusTotalDailyRequirements;
    }

    public Integer getbPlusTotalDailyRequirements() {
        return bPlusTotalDailyRequirements;
    }

    public void setbPlusTotalDailyRequirements(Integer bPlusTotalDailyRequirements) {
        this.bPlusTotalDailyRequirements = bPlusTotalDailyRequirements;
    }

    public Integer getbMinusTotalDailyRequirements() {
        return bMinusTotalDailyRequirements;
    }

    public void setbMinusTotalDailyRequirements(Integer bMinusTotalDailyRequirements) {
        this.bMinusTotalDailyRequirements = bMinusTotalDailyRequirements;
    }

    public Integer getAbPlusTotalDailyRequirements() {
        return abPlusTotalDailyRequirements;
    }

    public void setAbPlusTotalDailyRequirements(Integer abPlusTotalDailyRequirements) {
        this.abPlusTotalDailyRequirements = abPlusTotalDailyRequirements;
    }

    public Integer getAbMinusTotalDailyRequirements() {
        return abMinusTotalDailyRequirements;
    }

    public void setAbMinusTotalDailyRequirements(Integer abMinusTotalDailyRequirements) {
        this.abMinusTotalDailyRequirements = abMinusTotalDailyRequirements;
    }

    public Integer getTotalTotalDailyRequirements() {
        return totalTotalDailyRequirements;
    }

    public void setTotalTotalDailyRequirements(Integer totalTotalDailyRequirements) {
        this.totalTotalDailyRequirements = totalTotalDailyRequirements;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
