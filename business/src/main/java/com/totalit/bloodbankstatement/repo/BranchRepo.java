/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.Branch;

import java.util.List;


/**
 *
 * @author tasu
 */
public interface BranchRepo extends AbstractRepo<Branch, Long>{

    Branch findByBranchName(String name);


    List<Branch> findByBranchNameNot(String branchName);
}
