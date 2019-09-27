/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalit.bloodbankstatement.domain.config;

import javax.persistence.*;

/**
 *
 * @author tasu
 */
@Entity
public class Branch extends BaseEntity{
    
    @Column(unique = true)
    private String branchName;
    private String address;
    private String email;
    private String phoneNumber;
    private String officePhone;
    private String staticFacility;
    private String cbd;
    private String mob1;
    private String mob2;
    private String mob3;
    private Integer minStatic;
    private Integer minCbd;
    private Integer minMob1;
    private Integer minMob2;
    private Integer minMob3;

    
//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(name = "company_module", joinColumns = {
//        @JoinColumn(name = "company_id", nullable = false)}, inverseJoinColumns = {
//        @JoinColumn(name = "module_id", nullable = false)})
//    @JsonIgnoreProperties(value = { "userRoles", "location" })
//    private Set<Module> modules = new HashSet<>();
    
    
    public Branch() {
    }


    public Branch(Long id, String branchName, String address, String email, String phoneNumber, String officePhone, String staticFacility, String cbd, String mob1, String mob2, String mob3, Integer minStatic, Integer minCbd, Integer minMob1, Integer minMob2, Integer minMob3) {
        super(id);
        this.branchName = branchName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.officePhone = officePhone;
        this.staticFacility = staticFacility;
        this.cbd = cbd;
        this.mob1 = mob1;
        this.mob2 = mob2;
        this.mob3 = mob3;
        this.minStatic = minStatic;
        this.minCbd = minCbd;
        this.minMob1 = minMob1;
        this.minMob2 = minMob2;
        this.minMob3 = minMob3;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStaticFacility() {
        return staticFacility;
    }

    public void setStaticFacility(String staticFacility) {
        this.staticFacility = staticFacility;
    }

    public String getCbd() {
        return cbd;
    }

    public void setCbd(String cbd) {
        this.cbd = cbd;
    }

    public String getMob1() {
        return mob1;
    }

    public void setMob1(String mob1) {
        this.mob1 = mob1;
    }

    public String getMob2() {
        return mob2;
    }

    public void setMob2(String mob2) {
        this.mob2 = mob2;
    }

    public String getMob3() {
        return mob3;
    }

    public void setMob3(String mob3) {
        this.mob3 = mob3;
    }

    public Integer getMinStatic() {
        return minStatic;
    }

    public void setMinStatic(Integer minStatic) {
        this.minStatic = minStatic;
    }

    public Integer getMinCbd() {
        return minCbd;
    }

    public void setMinCbd(Integer minCbd) {
        this.minCbd = minCbd;
    }

    public Integer getMinMob1() {
        return minMob1;
    }

    public void setMinMob1(Integer minMob1) {
        this.minMob1 = minMob1;
    }

    public Integer getMinMob2() {
        return minMob2;
    }

    public void setMinMob2(Integer minMob2) {
        this.minMob2 = minMob2;
    }

    public Integer getMinMob3() {
        return minMob3;
    }

    public void setMinMob3(Integer minMob3) {
        this.minMob3 = minMob3;
    }
}
