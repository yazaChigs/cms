package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;

import java.util.Date;
import java.util.List;

public interface StockQuarantinedService extends GenericNameService<StockQuarantined> {
    StockQuarantined getByBranchAndActive(Branch branch, boolean active);

    StockQuarantined getByBranchAndDateAndActive(Branch branch, Date date, Boolean active);

    StockQuarantined getQuarantineByDate(SearchDTO searchDTO, Branch branch);

    List<StockQuarantined> getAllByActive(boolean active);

    Long getQuarantineByDateAndBranch(Branch branch, Date startDate, Date endDate, String column);

    StockQuarantined getByBranchAndActiveAndDateCreatedBetween(Branch branch, Boolean active, Date startDate, Date endDate);
}
