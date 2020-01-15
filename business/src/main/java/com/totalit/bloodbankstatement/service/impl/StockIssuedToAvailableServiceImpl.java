package com.totalit.bloodbankstatement.service.impl;

import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToAvailable;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToQuarantine;
import com.totalit.bloodbankstatement.repo.StockIssuedToAvailableRepo;
import com.totalit.bloodbankstatement.repo.StockIssuedToQuarantineRepo;
import com.totalit.bloodbankstatement.service.StockIssuedToAvailableService;
import com.totalit.bloodbankstatement.service.StockIssuedToQuarantineService;
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
public class StockIssuedToAvailableServiceImpl implements StockIssuedToAvailableService {


    @Resource
    private StockIssuedToAvailableRepo repo;

    @Resource
    private UserService userService;


    @Override
    public List<StockIssuedToAvailable> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockIssuedToAvailable get(Long id) {
        Optional<StockIssuedToAvailable> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundException("Unable to get stock received from quarantine : " + id);
        }
    }

    @Override
    public void delete(StockIssuedToAvailable stockIssuedToAvailable) {

    }

    @Override
    public List<StockIssuedToAvailable> getPageable() {
        return null;
    }

    @Override
    public StockIssuedToAvailable save(StockIssuedToAvailable t) {
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
    public StockIssuedToAvailable findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockIssuedToAvailable current, StockIssuedToAvailable old) {
        return null;
    }

    @Override
    public List<StockIssuedToAvailable> findAllByDateCreatedAndStockAvailable(Date date,  StockAvailable stockAvailable) {
        return repo.findAllByDateCreatedAndStockAvailable(date, stockAvailable);
    }
}
