/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalit.bloodbankstatement.domain.dto;


import com.totalit.bloodbankstatement.domain.config.Branch;

import java.io.Serializable;

/**
 *
 * @author kanaz
 */
public class BranchDto implements Serializable{

    private Long id;
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


    public BranchDto(Long id, String branchName, String address, String email, String phoneNumber, String officePhone, String staticFacility, String cbd, String mob1, String mob2, String mob3, Integer minStatic, Integer minCbd, Integer minMob1, Integer minMob2, Integer minMob3) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
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

    public Branch getInstance(BranchDto dto){
        Branch branch = new Branch(id, branchName, address, email, phoneNumber, officePhone, staticFacility, cbd, mob1, mob2, mob3, minStatic,  minCbd,  minMob1,  minMob2,  minMob3);
       return branch;
    }
    
}
