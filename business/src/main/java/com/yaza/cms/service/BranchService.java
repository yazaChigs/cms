/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.service;

import com.yaza.cms.domain.config.Branch;

import java.util.List;

/**
 *
 * @author tasu
 */
public interface BranchService extends GenericNameService<Branch>{
    List<Branch> getByUserBranch();

    List<Branch> getByUserBranch(Branch branch);
}
