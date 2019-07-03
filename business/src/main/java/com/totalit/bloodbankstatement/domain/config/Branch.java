/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalit.bloodbankstatement.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
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

    
//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(name = "company_module", joinColumns = {
//        @JoinColumn(name = "company_id", nullable = false)}, inverseJoinColumns = {
//        @JoinColumn(name = "module_id", nullable = false)})
//    @JsonIgnoreProperties(value = { "userRoles", "location" })
//    private Set<Module> modules = new HashSet<>();
    
    
    public Branch() {
    }

    public Branch(String branchName, String address, String email, String phoneNumber, String officePhone, Long id) {
        super(id);
        this.branchName = branchName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.officePhone = officePhone;
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
}
