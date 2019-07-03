package com.totalit.bloodbankstatement.repo.admin;

import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Admin.UnadjustedDailyRequirements;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.repo.AbstractRepo;

public interface UnadjustedDailyRequirementsRepo extends AbstractRepo<UnadjustedDailyRequirements, Long> {
    UnadjustedDailyRequirements findByBranch(Branch branch);
}
