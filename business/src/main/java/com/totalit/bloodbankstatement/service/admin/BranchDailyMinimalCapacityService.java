package com.totalit.bloodbankstatement.service.admin;

import com.totalit.bloodbankstatement.domain.config.Admin.BranchDailyMinimalCapacity;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.service.GenericNameService;

public interface BranchDailyMinimalCapacityService extends GenericNameService<BranchDailyMinimalCapacity> {
//    BranchDailyMinimalCapacity getByBranch(Branch branch);
    BranchDailyMinimalCapacity findFirstByOrderById();

}
