package com.totalit.bloodbankstatement.service.admin;

import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Admin.UnadjustedDailyRequirements;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.service.GenericNameService;

public interface UnadjustedDailyRequirementsService extends GenericNameService<UnadjustedDailyRequirements> {
//    UnadjustedDailyRequirements getByBranch(Branch branch);

    UnadjustedDailyRequirements findFirstByOrderById();
}
