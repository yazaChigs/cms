/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yaza.cms.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author tasu
 */
@Entity
@Table(name = "role")
public class UserRole extends BaseName{
    
    private static final long serialVersionUID = 1L;
    @Transient
    private String printName;
        
     @Transient
    private Boolean selected = Boolean.FALSE;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users = new HashSet<>();

//    @JsonIgnore
//    @ManyToMany(mappedBy = "userRoles")
//    private Set<Module> modules = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
    
    public String getPrintName(){
        return StringUtils.toCamelCase3(super.getName());
    }
    
}
