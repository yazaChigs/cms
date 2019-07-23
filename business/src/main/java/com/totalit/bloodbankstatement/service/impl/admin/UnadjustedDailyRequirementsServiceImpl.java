package com.totalit.bloodbankstatement.service.impl.admin;


import com.totalit.bloodbankstatement.domain.config.Admin.UnadjustedDailyRequirements;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.repo.admin.UnadjustedDailyRequirementsRepo;
import com.totalit.bloodbankstatement.service.UserService;
import com.totalit.bloodbankstatement.service.admin.UnadjustedDailyRequirementsService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UnadjustedDailyRequirementsServiceImpl implements UnadjustedDailyRequirementsService {

    @Resource
    private UnadjustedDailyRequirementsRepo repo;
    @Resource
    private UserService userService;

    @Override
    public UnadjustedDailyRequirements getByName(String name) {
        return null;
    }

    @Override
    public List<UnadjustedDailyRequirements> getAll() {
        return repo.findAll();
    }

    @Override
    public UnadjustedDailyRequirements get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(UnadjustedDailyRequirements unadjustedDailyRequirements) {

    }

    @Override
    public List<UnadjustedDailyRequirements> getPageable() {
        return null;
    }

    @Override
    public UnadjustedDailyRequirements save(UnadjustedDailyRequirements t) {
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
    public UnadjustedDailyRequirements findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(UnadjustedDailyRequirements current, UnadjustedDailyRequirements old) {
        return null;
    }


    @Override
    public UnadjustedDailyRequirements findFirstByOrderById() {
        return repo.findFirstByOrderById();
    }
}
