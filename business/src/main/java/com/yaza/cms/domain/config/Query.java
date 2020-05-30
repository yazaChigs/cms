/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.config;

import com.yaza.cms.domain.config.Admin.QueryType;
import com.yaza.cms.domain.util.FileInfo;
import com.yaza.cms.domain.util.Priority;
import com.yaza.cms.domain.util.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tasu
 */
@Entity
public class Query extends BaseEntity{

//    @Column(unique = true)

    private String fullname;
    private String phone;
    private String accountNumber;
    @ManyToOne
    private Branch branch;
    private String status;
    private String priority;
    @OneToOne
    private Category category;
    private String queryType;
    private String stanNo;
    private String amount;
    private String actionTaken;
    private String description;
    private String complaintDetails;
    private String phoneHome;
    private String phoneBusiness;
    private String fileName;
    @Transient
    private List<FileInfo> fileInfos = new ArrayList<>();


//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(name = "company_module", joinColumns = {
//        @JoinColumn(name = "company_id", nullable = false)}, inverseJoinColumns = {
//        @JoinColumn(name = "module_id", nullable = false)})
//    @JsonIgnoreProperties(value = { "userRoles", "location" })
//    private Set<Module> modules = new HashSet<>();


    public Query() {
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getStanNo() {
        return stanNo;
    }

    public void setStanNo(String stanNo) {
        this.stanNo = stanNo;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneBusiness() {
        return phoneBusiness;
    }

    public void setPhoneBusiness(String phoneBusiness) {
        this.phoneBusiness = phoneBusiness;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(List<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }
}
