package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;

import java.util.Date;

public interface StockAvailableRepo extends AbstractRepo<StockAvailable,Long> {
    StockAvailable getByBranchAndActive(Branch branch, Boolean active);
    StockAvailable findByBranchAndActiveAndDateCreatedBetween(Branch branch, Boolean active, Date startDate, Date endDate);
}
