package com.totalit.bloodbankstatement.domain.config.Admin;

import com.totalit.bloodbankstatement.domain.config.BaseEntity;
import com.totalit.bloodbankstatement.domain.config.Branch;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BranchDailyMinimalCapacity  extends BaseEntity {

    private Integer harareStaticCbd;
    private Integer harareMob1;
    private Integer harareMob2;
    private Integer harareMob3;
    private Integer harareTotalMinCapacity;
    private Integer harareExpectedDailyCollections;
    private Integer hararePercentageCapacity;
    private Integer bulawayoStaticCbd;
    private Integer bulawayoMob1;
    private Integer bulawayoMob2;
    private Integer bulawayoMob3;
    private Integer bulawayoTotalMinCapacity;
    private Integer bulawayoExpectedDailyCollections;
    private Integer bulawayoPercentageCapacity;
    private Integer gweruStaticCbd;
    private Integer gweruMob1;
    private Integer gweruMob2;
    private Integer gweruMob3;
    private Integer gweruTotalMinCapacity;
    private Integer gweruExpectedDailyCollections;
    private Integer gweruPercentageCapacity;
    private Integer mutareStaticCbd;
    private Integer mutareMob1;
    private Integer mutareMob2;
    private Integer mutareMob3;
    private Integer mutareTotalMinCapacity;
    private Integer mutareExpectedDailyCollections;
    private Integer mutarePercentageCapacity;
    private Integer masvingoStaticCbd;
    private Integer masvingoMob1;
    private Integer masvingoMob2;
    private Integer masvingoMob3;
    private Integer masvingoTotalMinCapacity;
    private Integer masvingoExpectedDailyCollections;
    private Integer masvingoPercentageCapacity;
    private Integer totalStaticCbd;
    private Integer totalMob1;
    private Integer totalMob2;
    private Integer totalMob3;
    private Integer totalTotalMinCapacity;
    private Integer totalExDailyCollections;
    private Integer totalPercentageCapacity;
    private Integer fixedPercentage;
    private Integer mobPercentage;

    public Integer getHarareStaticCbd() {
        return harareStaticCbd;
    }

    public void setHarareStaticCbd(Integer harareStaticCbd) {
        this.harareStaticCbd = harareStaticCbd;
    }

    public Integer getHarareMob1() {
        return harareMob1;
    }

    public void setHarareMob1(Integer harareMob1) {
        this.harareMob1 = harareMob1;
    }

    public Integer getHarareMob2() {
        return harareMob2;
    }

    public void setHarareMob2(Integer harareMob2) {
        this.harareMob2 = harareMob2;
    }

    public Integer getHarareMob3() {
        return harareMob3;
    }

    public void setHarareMob3(Integer harareMob3) {
        this.harareMob3 = harareMob3;
    }

    public Integer getHarareTotalMinCapacity() {
        return harareTotalMinCapacity;
    }

    public void setHarareTotalMinCapacity(Integer harareTotalMinCapacity) {
        this.harareTotalMinCapacity = harareTotalMinCapacity;
    }

    public Integer getHarareExpectedDailyCollections() {
        return harareExpectedDailyCollections;
    }

    public void setHarareExpectedDailyCollections(Integer harareExpectedDailyCollections) {
        this.harareExpectedDailyCollections = harareExpectedDailyCollections;
    }

    public Integer getHararePercentageCapacity() {
        return hararePercentageCapacity;
    }

    public void setHararePercentageCapacity(Integer hararePercentageCapacity) {
        this.hararePercentageCapacity = hararePercentageCapacity;
    }

    public Integer getBulawayoStaticCbd() {
        return bulawayoStaticCbd;
    }

    public void setBulawayoStaticCbd(Integer bulawayoStaticCbd) {
        this.bulawayoStaticCbd = bulawayoStaticCbd;
    }

    public Integer getBulawayoMob1() {
        return bulawayoMob1;
    }

    public void setBulawayoMob1(Integer bulawayoMob1) {
        this.bulawayoMob1 = bulawayoMob1;
    }

    public Integer getBulawayoMob2() {
        return bulawayoMob2;
    }

    public void setBulawayoMob2(Integer bulawayoMob2) {
        this.bulawayoMob2 = bulawayoMob2;
    }

    public Integer getBulawayoMob3() {
        return bulawayoMob3;
    }

    public void setBulawayoMob3(Integer bulawayoMob3) {
        this.bulawayoMob3 = bulawayoMob3;
    }

    public Integer getBulawayoTotalMinCapacity() {
        return bulawayoTotalMinCapacity;
    }

    public void setBulawayoTotalMinCapacity(Integer bulawayoTotalMinCapacity) {
        this.bulawayoTotalMinCapacity = bulawayoTotalMinCapacity;
    }

    public Integer getBulawayoExpectedDailyCollections() {
        return bulawayoExpectedDailyCollections;
    }

    public void setBulawayoExpectedDailyCollections(Integer bulawayoExpectedDailyCollections) {
        this.bulawayoExpectedDailyCollections = bulawayoExpectedDailyCollections;
    }

    public Integer getBulawayoPercentageCapacity() {
        return bulawayoPercentageCapacity;
    }

    public void setBulawayoPercentageCapacity(Integer bulawayoPercentageCapacity) {
        this.bulawayoPercentageCapacity = bulawayoPercentageCapacity;
    }

    public Integer getGweruStaticCbd() {
        return gweruStaticCbd;
    }

    public void setGweruStaticCbd(Integer gweruStaticCbd) {
        this.gweruStaticCbd = gweruStaticCbd;
    }

    public Integer getGweruMob1() {
        return gweruMob1;
    }

    public void setGweruMob1(Integer gweruMob1) {
        this.gweruMob1 = gweruMob1;
    }

    public Integer getGweruMob2() {
        return gweruMob2;
    }

    public void setGweruMob2(Integer gweruMob2) {
        this.gweruMob2 = gweruMob2;
    }

    public Integer getGweruMob3() {
        return gweruMob3;
    }

    public void setGweruMob3(Integer gweruMob3) {
        this.gweruMob3 = gweruMob3;
    }

    public Integer getGweruTotalMinCapacity() {
        return gweruTotalMinCapacity;
    }

    public void setGweruTotalMinCapacity(Integer gweruTotalMinCapacity) {
        this.gweruTotalMinCapacity = gweruTotalMinCapacity;
    }

    public Integer getGweruExpectedDailyCollections() {
        return gweruExpectedDailyCollections;
    }

    public void setGweruExpectedDailyCollections(Integer gweruExpectedDailyCollections) {
        this.gweruExpectedDailyCollections = gweruExpectedDailyCollections;
    }

    public Integer getGweruPercentageCapacity() {
        return gweruPercentageCapacity;
    }

    public void setGweruPercentageCapacity(Integer gweruPercentageCapacity) {
        this.gweruPercentageCapacity = gweruPercentageCapacity;
    }

    public Integer getMutareStaticCbd() {
        return mutareStaticCbd;
    }

    public void setMutareStaticCbd(Integer mutareStaticCbd) {
        this.mutareStaticCbd = mutareStaticCbd;
    }

    public Integer getMutareMob1() {
        return mutareMob1;
    }

    public void setMutareMob1(Integer mutareMob1) {
        this.mutareMob1 = mutareMob1;
    }

    public Integer getMutareMob2() {
        return mutareMob2;
    }

    public void setMutareMob2(Integer mutareMob2) {
        this.mutareMob2 = mutareMob2;
    }

    public Integer getMutareMob3() {
        return mutareMob3;
    }

    public void setMutareMob3(Integer mutareMob3) {
        this.mutareMob3 = mutareMob3;
    }

    public Integer getMutareTotalMinCapacity() {
        return mutareTotalMinCapacity;
    }

    public void setMutareTotalMinCapacity(Integer mutareTotalMinCapacity) {
        this.mutareTotalMinCapacity = mutareTotalMinCapacity;
    }

    public Integer getMutareExpectedDailyCollections() {
        return mutareExpectedDailyCollections;
    }

    public void setMutareExpectedDailyCollections(Integer mutareExpectedDailyCollections) {
        this.mutareExpectedDailyCollections = mutareExpectedDailyCollections;
    }

    public Integer getMutarePercentageCapacity() {
        return mutarePercentageCapacity;
    }

    public void setMutarePercentageCapacity(Integer mutarePercentageCapacity) {
        this.mutarePercentageCapacity = mutarePercentageCapacity;
    }

    public Integer getMasvingoStaticCbd() {
        return masvingoStaticCbd;
    }

    public void setMasvingoStaticCbd(Integer masvingoStaticCbd) {
        this.masvingoStaticCbd = masvingoStaticCbd;
    }

    public Integer getMasvingoMob1() {
        return masvingoMob1;
    }

    public void setMasvingoMob1(Integer masvingoMob1) {
        this.masvingoMob1 = masvingoMob1;
    }

    public Integer getMasvingoMob2() {
        return masvingoMob2;
    }

    public void setMasvingoMob2(Integer masvingoMob2) {
        this.masvingoMob2 = masvingoMob2;
    }

    public Integer getMasvingoMob3() {
        return masvingoMob3;
    }

    public void setMasvingoMob3(Integer masvingoMob3) {
        this.masvingoMob3 = masvingoMob3;
    }

    public Integer getMasvingoTotalMinCapacity() {
        return masvingoTotalMinCapacity;
    }

    public void setMasvingoTotalMinCapacity(Integer masvingoTotalMinCapacity) {
        this.masvingoTotalMinCapacity = masvingoTotalMinCapacity;
    }

    public Integer getMasvingoExpectedDailyCollections() {
        return masvingoExpectedDailyCollections;
    }

    public void setMasvingoExpectedDailyCollections(Integer masvingoExpectedDailyCollections) {
        this.masvingoExpectedDailyCollections = masvingoExpectedDailyCollections;
    }

    public Integer getMasvingoPercentageCapacity() {
        return masvingoPercentageCapacity;
    }

    public void setMasvingoPercentageCapacity(Integer masvingoPercentageCapacity) {
        this.masvingoPercentageCapacity = masvingoPercentageCapacity;
    }

    public Integer getTotalStaticCbd() {
        return totalStaticCbd;
    }

    public void setTotalStaticCbd(Integer totalStaticCbd) {
        this.totalStaticCbd = totalStaticCbd;
    }

    public Integer getTotalMob1() {
        return totalMob1;
    }

    public void setTotalMob1(Integer totalMob1) {
        this.totalMob1 = totalMob1;
    }

    public Integer getTotalMob2() {
        return totalMob2;
    }

    public void setTotalMob2(Integer totalMob2) {
        this.totalMob2 = totalMob2;
    }

    public Integer getTotalMob3() {
        return totalMob3;
    }

    public void setTotalMob3(Integer totalMob3) {
        this.totalMob3 = totalMob3;
    }

    public Integer getTotalTotalMinCapacity() {
        return totalTotalMinCapacity;
    }

    public void setTotalTotalMinCapacity(Integer totalTotalMinCapacity) {
        this.totalTotalMinCapacity = totalTotalMinCapacity;
    }

    public Integer getTotalExDailyCollections() {
        return totalExDailyCollections;
    }

    public void setTotalExDailyCollections(Integer totalExDailyCollections) {
        this.totalExDailyCollections = totalExDailyCollections;
    }

    public Integer getTotalPercentageCapacity() {
        return totalPercentageCapacity;
    }

    public void setTotalPercentageCapacity(Integer totalPercentageCapacity) {
        this.totalPercentageCapacity = totalPercentageCapacity;
    }

    public Integer getFixedPercentage() {
        return fixedPercentage;
    }

    public void setFixedPercentage(Integer fixedPercentage) {
        this.fixedPercentage = fixedPercentage;
    }

    public Integer getMobPercentage() {
        return mobPercentage;
    }

    public void setMobPercentage(Integer mobPercentage) {
        this.mobPercentage = mobPercentage;
    }

}
