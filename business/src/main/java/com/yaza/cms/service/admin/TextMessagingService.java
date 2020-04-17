/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service.admin;


import com.yaza.cms.domain.config.Admin.TextMessaging;
import com.yaza.cms.service.GenericService;

/**
 *
 * @author machigere
 */
public interface TextMessagingService extends GenericService<TextMessaging> {

    public TextMessaging findFirst();

    
}
