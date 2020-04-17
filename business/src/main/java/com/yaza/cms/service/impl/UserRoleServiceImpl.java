package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.UserRole;
import com.yaza.cms.repo.UserRoleRepo;
import com.yaza.cms.service.UserRoleService;
import com.yaza.cms.service.UserService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Judge Muzinda
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleRepo userRoleRepo;
    @Resource
    private UserService userService;

    @Override
    public List<UserRole> getAll() {
        return userRoleRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public UserRole get(Long id) {
        return userRoleRepo.findById(id).get();
    }

    @Override
    public void delete(UserRole t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        userRoleRepo.save(t);
    }

    @Override
    public List<UserRole> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public UserRole save(UserRole t) {
        if (t.getId() == null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return userRoleRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return userRoleRepo.save(t);
    }

    @Override
    public UserRole getByName(String name) {
        return userRoleRepo.findByName(name);
    }
    
     @Override
    public Boolean checkDuplicate(UserRole current, UserRole old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (userRoleRepo.getUserRoleByName(current.getName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (userRoleRepo.getUserRoleByName(current.getName()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserRole findByUuid(String uuid) {
        return userRoleRepo.findByUuid(uuid);
    }
}
