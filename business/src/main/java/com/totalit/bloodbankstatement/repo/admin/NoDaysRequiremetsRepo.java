package com.totalit.bloodbankstatement.repo.admin;

import com.totalit.bloodbankstatement.domain.config.Admin.BranchDailyMinimalCapacity;
import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.repo.AbstractRepo;

public interface NoDaysRequiremetsRepo extends AbstractRepo<NoDaysRequiremets, Long> {
    NoDaysRequiremets getByBranch(Branch branch);
}
