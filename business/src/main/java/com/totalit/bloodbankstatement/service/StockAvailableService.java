package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;
import com.totalit.bloodbankstatement.domain.dto.StockInfoDTO;

public interface StockAvailableService extends GenericNameService<StockAvailable> {
    StockAvailable getByBranchAndActive(Branch branch, Boolean active);

    StockInfoDTO getResult(SearchDTO dto);

    StockAvailable getAvailableByDate(SearchDTO searchDTO, Branch branch);
}
