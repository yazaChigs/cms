package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;

import java.util.Date;

public interface StockQuarantinedService extends GenericNameService<StockQuarantined> {
    StockQuarantined getByBranchAndActive(Branch branch, boolean active);

    StockQuarantined getByDate(Date date);

    StockQuarantined getQuarantineByDate(SearchDTO searchDTO, Branch branch);
}
