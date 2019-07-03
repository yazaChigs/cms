package com.totalit.bloodbankstatement.service.impl;

import com.totalit.bloodbankstatement.domain.config.StockIssuedToQuarantine;
import com.totalit.bloodbankstatement.repo.StockIssuedToQuarantineRepo;
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
public class StockIssuedToQuarantineServiceImpl implements StockIssuedToQuarantineService {


    @Resource
    private StockIssuedToQuarantineRepo repo;

    @Resource
    private UserService userService;

    @Override
    public List<StockIssuedToQuarantine> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockIssuedToQuarantine get(Long id) {
        Optional<StockIssuedToQuarantine> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundException("Unable to get PrimaryComplaint : " + id);
        }
    }

    @Override
    public void delete(StockIssuedToQuarantine stockIssuedToQuarantine) {

    }


    @Override
    public List<StockIssuedToQuarantine> getPageable() {
        return null;
    }

    @Override
    public StockIssuedToQuarantine save(StockIssuedToQuarantine t) {
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
    public StockIssuedToQuarantine findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockIssuedToQuarantine current, StockIssuedToQuarantine old) {
        return null;
    }
}
