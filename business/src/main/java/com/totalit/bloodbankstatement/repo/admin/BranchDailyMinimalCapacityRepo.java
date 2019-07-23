package com.totalit.bloodbankstatement.repo.admin;

import com.totalit.bloodbankstatement.domain.config.Admin.BranchDailyMinimalCapacity;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.repo.AbstractRepo;

public interface BranchDailyMinimalCapacityRepo extends AbstractRepo<BranchDailyMinimalCapacity, Long> {
//    BranchDailyMinimalCapacity getByBranch(Branch branch);
    BranchDailyMinimalCapacity findFirstByOrderById();

}
