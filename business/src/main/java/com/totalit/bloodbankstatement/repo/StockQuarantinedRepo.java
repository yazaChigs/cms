package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;

import java.util.List;

public interface StockQuarantinedRepo extends AbstractRepo<StockQuarantined,Long> {
    StockQuarantined findByBranchAndActive(Branch branch, boolean active);
}
