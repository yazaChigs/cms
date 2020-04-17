/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service.impl.admin;

import com.yaza.cms.domain.config.Admin.TextMessaging;
import com.yaza.cms.repo.admin.TextMessagingRepo;
import com.yaza.cms.service.admin.TextMessagingService;
import com.yaza.cms.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author machigere
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TextMessagingServiceImpl implements TextMessagingService {
    
    @Resource
    private TextMessagingRepo repo;
    @Resource
    private UserService userService;


    @Override
    public List<TextMessaging> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public TextMessaging get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<TextMessaging> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TextMessaging findFirst() {
        return repo.findFirstByOrderById();
    }

    @Override
    public Boolean checkDuplicate(TextMessaging current, TextMessaging old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TextMessaging t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TextMessaging save(TextMessaging t) {
    if (t.getId() == null) {
                      t.setCreatedBy(userService.getCurrentUser());
                      t.setDateCreated(new Date());
                      return repo.save(t);
                  }
              if(t.getCreatedById()!=null){
               t.setCreatedBy(userService.get(t.getCreatedById()));
           }
                      t.setModifiedBy(userService.getCurrentUser());
                  t.setDateModified(new Date());
                  return repo.save(t); 
    }

    @Override
    public TextMessaging findByUuid(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
