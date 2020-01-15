package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromAvailable;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromQuarantined;

import java.util.Date;
import java.util.List;

public interface StockReceivedFromAvailableRepo extends AbstractRepo<StockReceivedFromAvailable, Long> {
    List<StockReceivedFromAvailable> findAllByDateCreatedAndStockAvailable(Date date, StockAvailable stockAvailable);

}
