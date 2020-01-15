package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromQuarantined;

import java.util.Date;
import java.util.List;

public interface StockReceivedFromQuarantineRepo extends AbstractRepo<StockReceivedFromQuarantined, Long> {
    List<StockReceivedFromQuarantined> findAllByDateCreatedAndStockQuarantined(Date date, StockQuarantined stockQuarantined);

}
