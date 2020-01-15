package com.totalit.bloodbankstatement.service.impl;

import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromQuarantined;
import com.totalit.bloodbankstatement.repo.StockReceivedFromQuarantineRepo;
import com.totalit.bloodbankstatement.service.StockReceivedFromQuarantineService;
import com.totalit.bloodbankstatement.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class StockReceivedFromQuarantineServiceImpl implements StockReceivedFromQuarantineService {

    @Resource
    private StockReceivedFromQuarantineRepo repo;

    @Resource
    private UserService userService;

    @Override
    public List<StockReceivedFromQuarantined> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockReceivedFromQuarantined get(Long id) {
        Optional<StockReceivedFromQuarantined> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundException("Unable to get PrimaryComplaint : " + id);
        }
    }

    @Override
    public void delete(StockReceivedFromQuarantined stockReceivedFromQuarantined) {

    }

    @Override
    public List<StockReceivedFromQuarantined> getPageable() {
        return null;
    }

    @Override
    public StockReceivedFromQuarantined save(StockReceivedFromQuarantined t) {
        if (t.getId() == null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return repo.save(t);
        }
        if (t.getCreatedById() != null) {
            t.setCreatedBy(userService.get(t.getCreatedById()));
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return repo.save(t);
    }

    @Override
    public StockReceivedFromQuarantined findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockReceivedFromQuarantined current, StockReceivedFromQuarantined old) {
        return null;
    }


    @Override
    public List<StockReceivedFromQuarantined> findAllByDateCreatedAndStockQuarantined(Date date, StockQuarantined stockQuarantined) {
        return repo.findAllByDateCreatedAndStockQuarantined(date, stockQuarantined);
    }
}
