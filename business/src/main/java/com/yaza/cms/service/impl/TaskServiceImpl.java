/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.Query;
import com.yaza.cms.domain.config.Task;
import com.yaza.cms.domain.config.User;
import com.yaza.cms.repo.QueryRepo;
import com.yaza.cms.repo.TaskRepo;
import com.yaza.cms.service.QueryService;
import com.yaza.cms.service.TaskService;
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
public class TaskServiceImpl implements TaskService {
    
    @Resource
    private TaskRepo repo;
    @Resource
    private UserService userService;


    @Override
    public Task getByName(String name) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public Task get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Task t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        repo.save(t);
    }

    @Override
    public List<Task> getPageable() {
        return null;
    }

    @Override
    public Task save(Task t) {
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
    public Task findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(Task current, Task old) {
        return null;
    }


    @Override
    public List<Task> findByAssignee(User user) {
        return repo.findByAssignee(user);
    }

    @Override
    public List<Task> findByAssigneeAndStatusNot(User user, String status) {
        return repo.findByAssigneeAndStatusNot(user, status);
    }

    @Override
    public List<Task> findByAssigneeNotAndStatusNot(User user, String status) {
        return repo.findByAssigneeNotAndStatusNot(user, status);
    }

    @Override
    public Task findByQuery(Query query) {
        return repo.findByQuery(query);
    }

    @Override
    public List<Task> findByStatus(String status) {
        return repo.findByStatus(status);
    }

    @Override
    public List<Task> findByAssigneeAndStatus(User user, String status) {
        return repo.findByAssigneeAndStatus(user,status);
    }

    @Override
    public List<Task> findByAssigneeAndStatusAndActive(User user, String status, Boolean active) {
        return repo.findByAssigneeAndStatusAndActive(user,status , active);
    }

    @Override
    public List<Task> findByCreatedByAndStatusAndActive(User user, String status, Boolean active) {
        return repo.findByCreatedByAndStatusAndActive(user,status,active);
    }


}
