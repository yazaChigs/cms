package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;

import java.util.Date;
import java.util.List;

public interface StockQuarantinedRepo extends AbstractRepo<StockQuarantined,Long> {
    StockQuarantined findByBranchAndActive(Branch branch, boolean active);

    StockQuarantined findByBranchAndActiveAndDateCreatedBetween(Branch branch, Boolean active, Date startDate, Date endDate);

    StockQuarantined findByBranchAndDateCreatedAndActive(Branch branch, Date date, Boolean active);
}
