package com.totalit.bloodbankstatement.service.impl;

import com.totalit.bloodbankstatement.domain.config.StockReceivedFromAvailable;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromQuarantined;
import com.totalit.bloodbankstatement.repo.StockReceivedFromAvailableRepo;
import com.totalit.bloodbankstatement.repo.StockReceivedFromQuarantineRepo;
import com.totalit.bloodbankstatement.service.StockReceivedFromAvailableService;
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
public class StockReceivedFromAvailableServiceImpl implements StockReceivedFromAvailableService {

    @Resource
    private StockReceivedFromAvailableRepo repo;

    @Resource
    private UserService userService;


    @Override
    public List<StockReceivedFromAvailable> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockReceivedFromAvailable get(Long id) {
        Optional<StockReceivedFromAvailable> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundException("Unable to get stock received from quarantine : " + id);
        }
    }

    @Override
    public void delete(StockReceivedFromAvailable stockReceivedFromAvailable) {

    }

    @Override
    public List<StockReceivedFromAvailable> getPageable() {
        return null;
    }

    @Override
    public StockReceivedFromAvailable save(StockReceivedFromAvailable t) {
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
    public StockReceivedFromAvailable findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockReceivedFromAvailable current, StockReceivedFromAvailable old) {
        return null;
    }
}
