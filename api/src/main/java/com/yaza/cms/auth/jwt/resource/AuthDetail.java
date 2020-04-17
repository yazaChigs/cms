/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.auth.jwt.resource;

import com.yaza.cms.domain.config.User;

/**
 *
 * @author roy
 */
public class AuthDetail {
     private String token;
    private User user;

    public AuthDetail(){

    }

    public AuthDetail(String token, User user){
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   
}
