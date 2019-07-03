package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;

public interface StockQuarantinedService extends GenericNameService<StockQuarantined> {
    StockQuarantined getByBranchAndActive(Branch branch, boolean active);
}
