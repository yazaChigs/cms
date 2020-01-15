package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromQuarantined;

import java.util.Date;
import java.util.List;

public interface StockReceivedFromQuarantineService extends GenericService<StockReceivedFromQuarantined> {
    List<StockReceivedFromQuarantined> findAllByDateCreatedAndStockQuarantined(Date date, StockQuarantined stockQuarantined);

}
