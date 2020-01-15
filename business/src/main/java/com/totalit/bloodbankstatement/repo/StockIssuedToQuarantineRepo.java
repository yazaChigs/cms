package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToQuarantine;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;

import java.util.Date;
import java.util.List;

public interface StockIssuedToQuarantineRepo extends AbstractRepo<StockIssuedToQuarantine, Long> {
    List<StockIssuedToQuarantine> findAllByDateCreatedAndStockQuarantined(Date date, StockQuarantined stockQuarantined);
}
