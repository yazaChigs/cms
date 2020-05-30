/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.config;

import com.yaza.cms.domain.util.Priority;
import com.yaza.cms.domain.util.Status;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author tasu
 */
@Entity
public class Task extends BaseEntity{

//    @Column(unique = true)


    private String status;
    private  String priority;
    private String managersNotes;
    private Double spentTime;
    private String assigneeNotes;
    private Double actualTimeSpent;
    private String working;
    private Date startTime;
    private String actionTaken;
    @OneToOne
    private  Query query;

//    @Column(name="assignee",nullable = true)
//    @ManyToOne(cascade= CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.MERGE)
    private User assignee;

    public Task() {
    }


//    public Branch getBranch() {
//        return branch;
//    }
//
//    public void setBranch(Branch branch) {
//        this.branch = branch;
//    }


    public String getAssigneeNotes() {
        return assigneeNotes;
    }

    public void setAssigneeNotes(String assigneeNotes) {
        this.assigneeNotes = assigneeNotes;
    }

    public Double getActualTimeSpent() {
        return actualTimeSpent;
    }

    public void setActualTimeSpent(Double actualTimeSpent) {
        this.actualTimeSpent = actualTimeSpent;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
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

    public String getManagersNotes() {
        return managersNotes;
    }

    public void setManagersNotes(String managersNotes) {
        this.managersNotes = managersNotes;
    }

    public Double getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Double spentTime) {
        this.spentTime = spentTime;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }
}
