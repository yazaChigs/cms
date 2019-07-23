package com.totalit.bloodbankstatement.service.admin;

import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.service.GenericNameService;

public interface NoDaysRequiremetsService extends GenericNameService<NoDaysRequiremets> {
//    NoDaysRequiremets getByBranch(Branch branch);
    NoDaysRequiremets findFirstByOrderById();

}
