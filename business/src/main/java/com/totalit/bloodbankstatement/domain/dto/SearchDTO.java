package com.totalit.bloodbankstatement.domain.dto;

import com.totalit.bloodbankstatement.domain.config.Branch;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SearchDTO implements Serializable {
     private List<Branch> branches;
     private Date date;

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
