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

    public BranchDto(Long id,String branchName, String address, String email, String phoneNumber, String officePhone) {
        this.id = id;
        this.branchName = branchName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.officePhone = officePhone;
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

    public Branch getInstance(BranchDto dto){
        Branch branch = new Branch(branchName, address, email, phoneNumber, officePhone, id);
       return branch;
    }
    
}
