package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToQuarantine;

import java.util.Date;
import java.util.List;

public interface StockIssuedToAvailableService extends GenericService<StockIssuedToAvailable> {
    List<StockIssuedToAvailable> findAllByDateCreatedAndStockAvailable(Date date, StockAvailable stockAvailable);
}
