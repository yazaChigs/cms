/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.repo.BranchRepo;
import com.yaza.cms.service.BranchService;
import com.yaza.cms.service.UserService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BranchServiceImpl implements BranchService {
    
    @Resource
    private BranchRepo repo;
    @Resource
    private UserService userService;

    @Override
    public List<Branch> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public Branch get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Branch t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        repo.save(t);
    }

    @Override
    public List<Branch> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Branch save(Branch t) {
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
    public Branch getByName(String name) {
        return repo.findByBranchName(name);
    }
    
     @Override
    public Boolean checkDuplicate(Branch current, Branch old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getBranchName().equals(old.getBranchName())) {
                if (getByName(current.getBranchName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByName(current.getBranchName()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Branch findByUuid(String uuid) {
 return repo.findByUuid(uuid);    }

    @Override
    public List<Branch> getByUserBranch() {
        return null;
    }

    @Override
    public List<Branch> getByUserBranch(Branch branch) {
        return repo.findByBranchNameNot(branch.getBranchName());
    }

//    @Override
//    public Boolean checkDuplicate(Branch current, Branch old) {
//        return null;
//    }
}
