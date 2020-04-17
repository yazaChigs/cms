/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.Query;
import com.yaza.cms.repo.BranchRepo;
import com.yaza.cms.repo.QueryRepo;
import com.yaza.cms.service.BranchService;
import com.yaza.cms.service.QueryService;
import com.yaza.cms.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class QueryServiceImpl implements QueryService {
    
    @Resource
    private QueryRepo repo;
    @Resource
    private UserService userService;


    @Override
    public Query getByName(String name) {
        return null;
    }

    @Override
    public List<Query> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public Query get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Query t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        repo.save(t);
    }

    @Override
    public List<Query> getPageable() {
        return null;
    }

    @Override
    public Query save(Query t) {
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
    public Query findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(Query current, Query old) {
        return null;
    }

    @Override
    public List<Query> findAllByActive(Boolean active) {
        return repo.findAllByActive(active);
    }

    @Override
    public List<Query> findByStatusAndActive(String status, Boolean active) {
        return repo.findByStatusAndActive(status, active);
    }

    @Override
    public List<Query> findByStatus(String status) {
        return repo.findByStatus(status);
    }


}
