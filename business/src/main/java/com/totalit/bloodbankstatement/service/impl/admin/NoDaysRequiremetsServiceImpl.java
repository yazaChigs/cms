package com.totalit.bloodbankstatement.service.impl.admin;

import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.repo.admin.NoDaysRequiremetsRepo;
import com.totalit.bloodbankstatement.service.UserService;
import com.totalit.bloodbankstatement.service.admin.NoDaysRequiremetsService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class NoDaysRequiremetsServiceImpl implements NoDaysRequiremetsService {

    @Resource
    private NoDaysRequiremetsRepo repo;
    @Resource
    private UserService userService;

    @Override
    public NoDaysRequiremets getByName(String name) {
        return null;
    }

    @Override
    public List<NoDaysRequiremets> getAll() {
        return repo.findAll();
    }

    @Override
    public NoDaysRequiremets get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(NoDaysRequiremets noDaysRequiremets) {

    }

    @Override
    public List<NoDaysRequiremets> getPageable() {
        return null;
    }

    @Override
    public NoDaysRequiremets save(NoDaysRequiremets t) {
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
    public NoDaysRequiremets findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(NoDaysRequiremets current, NoDaysRequiremets old) {
        return null;
    }

    @Override
    public NoDaysRequiremets getByBranch(Branch branch) {
        return repo.getByBranch(branch);
    }
}
