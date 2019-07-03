package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;

public interface StockQuarantinedRepo extends AbstractRepo<StockQuarantined,Long> {
    StockQuarantined findByBranchAndActive(Branch branch, boolean active);
}
