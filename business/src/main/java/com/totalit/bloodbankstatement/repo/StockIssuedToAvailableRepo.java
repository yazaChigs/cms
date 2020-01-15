package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToQuarantine;

import java.util.Date;
import java.util.List;

public interface StockIssuedToAvailableRepo extends AbstractRepo<StockIssuedToAvailable, Long> {
    List<StockIssuedToAvailable> findAllByDateCreatedAndStockAvailable(Date date, StockAvailable stockAvailable);
}
