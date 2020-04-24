package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.Admin.CardQueries;
import com.yaza.cms.domain.config.Admin.MobileBanking;
import com.yaza.cms.repo.CardQueryRepo;
import com.yaza.cms.repo.MobileBankingRepo;
import com.yaza.cms.service.CardQueryService;
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
public class CardQueryServiceImpl implements CardQueryService {

    @Resource
    private CardQueryRepo repo;

    @Resource
    private UserService userService;


    @Override
    public CardQueries getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<CardQueries> getAll() {
        return repo.findAll();
    }

    @Override
    public CardQueries get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(CardQueries t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        repo.save(t);
    }

    @Override
    public List<CardQueries> getPageable() {
        return null;
    }

    @Override
    public CardQueries save(CardQueries t) {
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
    public CardQueries findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(CardQueries current, CardQueries old) {
        return null;
    }
}
