package com.totalit.bloodbankstatement.domain.config.Admin;

import com.totalit.bloodbankstatement.domain.config.BaseEntity;
import com.totalit.bloodbankstatement.domain.config.Branch;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NoDaysRequiremets extends BaseEntity {

    private Integer harareOplus;
    private Integer harareOminus;
    private Integer harareAplus;
    private Integer harareBplus;
    private Integer harareTotal;
    private Integer harareNationalPercentage;
    private Integer bulawayoOplus;
    private Integer bulawayoOminus;
    private Integer bulawayoAplus;
    private Integer bulawayoBplus;
    private Integer bulawayoTotal;
    private Integer bulawayoNationalPercentage;
    private Integer gweruOplus;
    private Integer gweruOminus;
    private Integer gweruAplus;
    private Integer gweruBplus;
    private Integer gweruTotal;
    private Integer gweruNationalPercentage;
    private Integer mutareOplus;
    private Integer mutareOminus;
    private Integer mutareAplus;
    private Integer mutareBplus;
    private Integer mutareTotal;
    private Integer mutareNationalPercentage;
    private Integer masvingoOplus;
    private Integer masvingoOminus;
    private Integer masvingoAplus;
    private Integer masvingoBplus;
    private Integer masvingoTotal;
    private Integer masvingoNationalPercentage;
    private Integer nationalRequirementsOplus;
    private Integer nationalRequirementsOminus;
    private Integer nationalRequirementsAplus;
    private Integer nationalRequirementsBplus;
    private Integer nationalRequirementsTotal;
    private Integer nationalRequirementsNationalPercentage;

    @ManyToOne
    private Branch branch;

    public Integer getHarareOplus() {
        return harareOplus;
    }

    public void setHarareOplus(Integer harareOplus) {
        this.harareOplus = harareOplus;
    }

    public Integer getHarareOminus() {
        return harareOminus;
    }

    public void setHarareOminus(Integer harareOminus) {
        this.harareOminus = harareOminus;
    }

    public Integer getHarareAplus() {
        return harareAplus;
    }

    public void setHarareAplus(Integer harareAplus) {
        this.harareAplus = harareAplus;
    }

    public Integer getHarareBplus() {
        return harareBplus;
    }

    public void setHarareBplus(Integer harareBplus) {
        this.harareBplus = harareBplus;
    }

    public Integer getHarareTotal() {
        return harareTotal;
    }

    public void setHarareTotal(Integer harareTotal) {
        this.harareTotal = harareTotal;
    }

    public Integer getHarareNationalPercentage() {
        return harareNationalPercentage;
    }

    public void setHarareNationalPercentage(Integer harareNationalPercentage) {
        this.harareNationalPercentage = harareNationalPercentage;
    }

    public Integer getBulawayoOplus() {
        return bulawayoOplus;
    }

    public void setBulawayoOplus(Integer bulawayoOplus) {
        this.bulawayoOplus = bulawayoOplus;
    }

    public Integer getBulawayoOminus() {
        return bulawayoOminus;
    }

    public void setBulawayoOminus(Integer bulawayoOminus) {
        this.bulawayoOminus = bulawayoOminus;
    }

    public Integer getBulawayoAplus() {
        return bulawayoAplus;
    }

    public void setBulawayoAplus(Integer bulawayoAplus) {
        this.bulawayoAplus = bulawayoAplus;
    }

    public Integer getBulawayoBplus() {
        return bulawayoBplus;
    }

    public void setBulawayoBplus(Integer bulawayoBplus) {
        this.bulawayoBplus = bulawayoBplus;
    }

    public Integer getBulawayoTotal() {
        return bulawayoTotal;
    }

    public void setBulawayoTotal(Integer bulawayoTotal) {
        this.bulawayoTotal = bulawayoTotal;
    }

    public Integer getBulawayoNationalPercentage() {
        return bulawayoNationalPercentage;
    }

    public void setBulawayoNationalPercentage(Integer bulawayoNationalPercentage) {
        this.bulawayoNationalPercentage = bulawayoNationalPercentage;
    }

    public Integer getGweruOplus() {
        return gweruOplus;
    }

    public void setGweruOplus(Integer gweruOplus) {
        this.gweruOplus = gweruOplus;
    }

    public Integer getGweruOminus() {
        return gweruOminus;
    }

    public void setGweruOminus(Integer gweruOminus) {
        this.gweruOminus = gweruOminus;
    }

    public Integer getGweruAplus() {
        return gweruAplus;
    }

    public void setGweruAplus(Integer gweruAplus) {
        this.gweruAplus = gweruAplus;
    }

    public Integer getGweruBplus() {
        return gweruBplus;
    }

    public void setGweruBplus(Integer gweruBplus) {
        this.gweruBplus = gweruBplus;
    }

    public Integer getGweruTotal() {
        return gweruTotal;
    }

    public void setGweruTotal(Integer gweruTotal) {
        this.gweruTotal = gweruTotal;
    }

    public Integer getGweruNationalPercentage() {
        return gweruNationalPercentage;
    }

    public void setGweruNationalPercentage(Integer gweruNationalPercentage) {
        this.gweruNationalPercentage = gweruNationalPercentage;
    }

    public Integer getMutareOplus() {
        return mutareOplus;
    }

    public void setMutareOplus(Integer mutareOplus) {
        this.mutareOplus = mutareOplus;
    }

    public Integer getMutareOminus() {
        return mutareOminus;
    }

    public void setMutareOminus(Integer mutareOminus) {
        this.mutareOminus = mutareOminus;
    }

    public Integer getMutareAplus() {
        return mutareAplus;
    }

    public void setMutareAplus(Integer mutareAplus) {
        this.mutareAplus = mutareAplus;
    }

    public Integer getMutareBplus() {
        return mutareBplus;
    }

    public void setMutareBplus(Integer mutareBplus) {
        this.mutareBplus = mutareBplus;
    }

    public Integer getMutareTotal() {
        return mutareTotal;
    }

    public void setMutareTotal(Integer mutareTotal) {
        this.mutareTotal = mutareTotal;
    }

    public Integer getMutareNationalPercentage() {
        return mutareNationalPercentage;
    }

    public void setMutareNationalPercentage(Integer mutareNationalPercentage) {
        this.mutareNationalPercentage = mutareNationalPercentage;
    }

    public Integer getMasvingoOplus() {
        return masvingoOplus;
    }

    public void setMasvingoOplus(Integer masvingoOplus) {
        this.masvingoOplus = masvingoOplus;
    }

    public Integer getMasvingoOminus() {
        return masvingoOminus;
    }

    public void setMasvingoOminus(Integer masvingoOminus) {
        this.masvingoOminus = masvingoOminus;
    }

    public Integer getMasvingoAplus() {
        return masvingoAplus;
    }

    public void setMasvingoAplus(Integer masvingoAplus) {
        this.masvingoAplus = masvingoAplus;
    }

    public Integer getMasvingoBplus() {
        return masvingoBplus;
    }

    public void setMasvingoBplus(Integer masvingoBplus) {
        this.masvingoBplus = masvingoBplus;
    }

    public Integer getMasvingoTotal() {
        return masvingoTotal;
    }

    public void setMasvingoTotal(Integer masvingoTotal) {
        this.masvingoTotal = masvingoTotal;
    }

    public Integer getMasvingoNationalPercentage() {
        return masvingoNationalPercentage;
    }

    public void setMasvingoNationalPercentage(Integer masvingoNationalPercentage) {
        this.masvingoNationalPercentage = masvingoNationalPercentage;
    }

    public Integer getNationalRequirementsOplus() {
        return nationalRequirementsOplus;
    }

    public void setNationalRequirementsOplus(Integer nationalRequirementsOplus) {
        this.nationalRequirementsOplus = nationalRequirementsOplus;
    }

    public Integer getNationalRequirementsOminus() {
        return nationalRequirementsOminus;
    }

    public void setNationalRequirementsOminus(Integer nationalRequirementsOminus) {
        this.nationalRequirementsOminus = nationalRequirementsOminus;
    }

    public Integer getNationalRequirementsAplus() {
        return nationalRequirementsAplus;
    }

    public void setNationalRequirementsAplus(Integer nationalRequirementsAplus) {
        this.nationalRequirementsAplus = nationalRequirementsAplus;
    }

    public Integer getNationalRequirementsBplus() {
        return nationalRequirementsBplus;
    }

    public void setNationalRequirementsBplus(Integer nationalRequirementsBplus) {
        this.nationalRequirementsBplus = nationalRequirementsBplus;
    }

    public Integer getNationalRequirementsTotal() {
        return nationalRequirementsTotal;
    }

    public void setNationalRequirementsTotal(Integer nationalRequirementsTotal) {
        this.nationalRequirementsTotal = nationalRequirementsTotal;
    }

    public Integer getNationalRequirementsNationalPercentage() {
        return nationalRequirementsNationalPercentage;
    }

    public void setNationalRequirementsNationalPercentage(Integer nationalRequirementsNationalPercentage) {
        this.nationalRequirementsNationalPercentage = nationalRequirementsNationalPercentage;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
