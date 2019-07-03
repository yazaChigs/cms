package com.totalit.bloodbankstatement.service.impl;

import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.repo.StockAvailableRepo;
import com.totalit.bloodbankstatement.repo.StockQuarantinedRepo;
import com.totalit.bloodbankstatement.service.StockQuarantinedService;
import com.totalit.bloodbankstatement.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class StockQuarantinedServiceImpl implements StockQuarantinedService {

    @Resource
    private StockQuarantinedRepo repo;
    @Resource
    private UserService userService;

    @Override
    public StockQuarantined getByName(String name) {
        return null;
    }

    @Override
    public List<StockQuarantined> getAll() {
            return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockQuarantined get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(StockQuarantined stockQuarantined) {

    }

    @Override
    public List<StockQuarantined> getPageable() {
        return null;
    }

    @Override
    public StockQuarantined save(StockQuarantined t) {
        if (t.getId() == null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return repo.save(t);
        }
        if(t.getCreatedById()!=null){
            t.setCreatedBy(userService.get(t.getCreatedById()));
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return repo.save(t);
    }

    @Override
    public StockQuarantined findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockQuarantined current, StockQuarantined old) {
        return null;
    }

    @Override
    public StockQuarantined getByBranchAndActive(Branch branch, boolean active) {
        return repo.findByBranchAndActive(branch, active);
    }
}
