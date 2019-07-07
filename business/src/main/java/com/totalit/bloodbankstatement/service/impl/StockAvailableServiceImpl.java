package com.totalit.bloodbankstatement.service.impl;


import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.repo.StockAvailableRepo;
import com.totalit.bloodbankstatement.service.StockAvailableService;
import com.totalit.bloodbankstatement.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class StockAvailableServiceImpl implements StockAvailableService {

    @Resource
    private StockAvailableRepo repo;
    @Resource
    private UserService userService;

    @Override
    public StockAvailable getByName(String name) {
        return null;
    }

    @Override
    public List<StockAvailable> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockAvailable get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(StockAvailable stockAvailable) {

    }

    @Override
    public List<StockAvailable> getPageable() {
        return null;
    }

    @Override
    public StockAvailable save(StockAvailable t) {
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
    public StockAvailable findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockAvailable current, StockAvailable old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getId().equals(old.getId())) {
                if (get(current.getId()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (get(current.getId())  != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public StockAvailable getByBranchAndActive(Branch branch, Boolean active) {
        return repo.getByBranchAndActive(branch, active);
    }
}
