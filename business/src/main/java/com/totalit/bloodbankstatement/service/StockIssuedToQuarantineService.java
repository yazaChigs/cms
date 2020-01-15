package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToQuarantine;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;

import java.util.Date;
import java.util.List;

public interface StockIssuedToQuarantineService extends GenericService<StockIssuedToQuarantine> {
    List<StockIssuedToQuarantine> findAllByDateCreatedAndStockQuarantined(Date date, StockQuarantined stockQuarantined);

}
