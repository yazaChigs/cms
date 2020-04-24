/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service;

import com.yaza.cms.domain.config.Admin.CardQueries;
import com.yaza.cms.domain.config.Admin.MobileBanking;
import com.yaza.cms.domain.config.Admin.QueryType;
import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.Category;
import com.yaza.cms.domain.config.Query;

import java.util.List;

/**
 *
 * @author tasu
 */
public interface QueryService extends GenericNameService<Query>{
    public List<Query> findAllByActive(Boolean active);
    public List<Query> findByStatusAndActive(String status,Boolean active);
    public List<Query> findByStatus(String status);
    public List<Query> findByQueryType(QueryType queryType);
    public List<Query> findByCategory(Category category);

//    public List<Query> findByCardQueries(CardQueries cardQueries);
//    public List<Query> findByMobileBanking(MobileBanking mobileBanking);

}
