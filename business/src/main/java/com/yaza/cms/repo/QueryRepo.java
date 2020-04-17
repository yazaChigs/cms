/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.repo;

import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.Query;

import java.util.List;


/**
 *
 * @author tasu
 */
public interface QueryRepo extends AbstractRepo<Query, Long>{
    public List<Query> findAllByActive(Boolean active);
    public List<Query> findByStatusAndActive(String status,Boolean active);
    public List<Query> findByStatus(String status);


}
