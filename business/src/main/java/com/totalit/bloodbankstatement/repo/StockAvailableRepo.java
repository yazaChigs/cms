package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;

public interface StockAvailableRepo extends AbstractRepo<StockAvailable,Long> {
    StockAvailable getByBranchAndActive(Branch branch, Boolean active);
}
