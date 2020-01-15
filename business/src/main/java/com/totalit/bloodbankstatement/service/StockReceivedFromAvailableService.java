package com.totalit.bloodbankstatement.service;

import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromAvailable;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromQuarantined;
import com.totalit.bloodbankstatement.repo.StockReceivedFromAvailableRepo;

import java.util.Date;
import java.util.List;

public interface StockReceivedFromAvailableService extends GenericService<StockReceivedFromAvailable> {
    List<StockReceivedFromAvailable> findAllByDateCreatedAndStockAvailable(Date date, StockAvailable stockAvailable);

}
