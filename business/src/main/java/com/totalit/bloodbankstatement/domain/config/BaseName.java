/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalit.bloodbankstatement.domain.config;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author tasu
 */
@MappedSuperclass
public class BaseName extends BaseEntity{
    
    private String name;
    private String description;

    public BaseName(String name, String description, Long id) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public BaseName(){
        
    }
    
    public BaseName(User createdBy, User modifiedBy, Date dateCreated, Date dateModified, String name){
        super(createdBy, modifiedBy, dateCreated, dateModified);
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 /*   
    @Transient
    public String getShortName(){
        String raw = name.trim();
        if(raw.split("\\s").length > 1){
            String output = "";
            String [] arr = raw.split("\\s");
            if(arr[0].length() >= 3){
                output += arr[0].substring(0, 3);
            }
            if(arr[1].length() >= 3){
                output += " "+arr[1];
            }
            return StringUtils.toCamelCase2(output);
        }
        if(name.length() >= 7)
            return getName().substring(0, 6);
        return getName();
    }
*/
}
