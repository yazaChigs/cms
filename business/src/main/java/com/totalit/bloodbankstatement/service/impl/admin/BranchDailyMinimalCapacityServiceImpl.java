package com.totalit.bloodbankstatement.service.impl.admin;


import com.totalit.bloodbankstatement.domain.config.Admin.BranchDailyMinimalCapacity;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.repo.admin.BranchDailyMinimalCapacityRepo;
import com.totalit.bloodbankstatement.service.UserService;
import com.totalit.bloodbankstatement.service.admin.BranchDailyMinimalCapacityService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BranchDailyMinimalCapacityServiceImpl implements BranchDailyMinimalCapacityService {

    @Resource
    private BranchDailyMinimalCapacityRepo repo;
    @Resource
    private UserService userService;

    @Override
    public BranchDailyMinimalCapacity getByName(String name) {
        return null;
    }

    @Override
    public List<BranchDailyMinimalCapacity> getAll() {
        return repo.findAll();
    }

    @Override
    public BranchDailyMinimalCapacity get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(BranchDailyMinimalCapacity branchDailyMinimalCapacity) {

    }

    @Override
    public List<BranchDailyMinimalCapacity> getPageable() {
        return null;
    }

    @Override
    public BranchDailyMinimalCapacity save(BranchDailyMinimalCapacity t) {
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
    public BranchDailyMinimalCapacity findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(BranchDailyMinimalCapacity current, BranchDailyMinimalCapacity old) {
//
//        if (current.getId() != null) {
//            /**
//             * @param current is in existence
//             */
//            if (!current.getBranch().equals(old.getBranch())) {
//                if (getByName(current.getBranch().getBranchName()) != null) {
//                    return true;
//                }
//            }
//
//        } else if (current.getId() == null) {
//            /**
//             * @param current is new
//             */
//            if (getByName(current.getBranch().getBranchName()) != null) {
//                return true;
//            }
//        }
        return null;
    }


    @Override
    public BranchDailyMinimalCapacity findFirstByOrderById() {
        return repo.findFirstByOrderById();
    }
}
