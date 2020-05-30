/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.repo;

import com.yaza.cms.domain.config.Query;
import com.yaza.cms.domain.config.Task;
import com.yaza.cms.domain.config.User;

import java.util.List;


/**
 *
 * @author tasu
 */
public interface TaskRepo extends AbstractRepo<Task, Long>{
    public List<Task> findByAssignee(User user);
    public List<Task> findByAssigneeAndStatusNot(User user, String status);
    public List<Task> findByAssigneeNotAndStatusNot(User user, String status);
    public List<Task> findByAssigneeAndStatus(User user, String status);
    public  Task findByQuery(Query query);
    public  List<Task> findByStatus(String status);
    public List<Task> findByAssigneeAndStatusAndActive(User user, String status, Boolean active);
    public List<Task> findByCreatedByAndStatusAndActive(User user, String status, Boolean active);



}
