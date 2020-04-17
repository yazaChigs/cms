/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.repo.admin;


import com.yaza.cms.domain.config.Admin.TextMessaging;
import com.yaza.cms.repo.AbstractRepo;

/**
 *
 * @author machigere
 */
public interface TextMessagingRepo extends AbstractRepo<TextMessaging, Long> {

    public TextMessaging findFirstByOrderById();

    
}
