/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service;

import com.yaza.cms.domain.config.Query;
import com.yaza.cms.domain.config.Task;
import com.yaza.cms.domain.config.User;

import java.util.List;

/**
 *
 * @author tasu
 */
public interface TaskService extends GenericNameService<Task>{
    public List<Task> findByAssignee(User user);
    public List<Task> findByAssigneeAndStatusNot(User user, String status);
    public List<Task> findByAssigneeNotAndStatusNot(User user, String status);
    public  Task findByQuery(Query query);


}
