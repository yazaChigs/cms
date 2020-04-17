/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.config.Admin;

import com.yaza.cms.domain.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author machigere
 */
@Entity
@Table(name = "text_messaging")
public class TextMessaging extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String username;
    private String webServiceToken;
    private String message;
    private String instructions;
    


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebServiceToken() {
        return webServiceToken;
    }

    public void setWebServiceToken(String webServiceToken) {
        this.webServiceToken = webServiceToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    
 
}
