package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.Admin.MobileBanking;
import com.yaza.cms.domain.config.Category;
import com.yaza.cms.repo.CategoryRepo;
import com.yaza.cms.repo.MobileBankingRepo;
import com.yaza.cms.service.CategoryService;
import com.yaza.cms.service.MobileBankngService;
import com.yaza.cms.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class MobileBankingServiceImpl implements MobileBankngService {

    @Resource
    private MobileBankingRepo repo;

    @Resource
    private UserService userService;


    @Override
    public MobileBanking getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<MobileBanking> getAll() {
        return repo.findAll();
    }

    @Override
    public MobileBanking get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(MobileBanking t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        repo.save(t);
    }

    @Override
    public List<MobileBanking> getPageable() {
        return null;
    }

    @Override
    public MobileBanking save(MobileBanking t) {
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
    public MobileBanking findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(MobileBanking current, MobileBanking old) {
        return null;
    }
}
