package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;

public interface StockAvailableService extends GenericNameService<StockAvailable> {
    StockAvailable getByBranchAndActive(Branch branch, Boolean active);
}
