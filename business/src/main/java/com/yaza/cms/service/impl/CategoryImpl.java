package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.Category;
import com.yaza.cms.repo.CategoryRepo;
import com.yaza.cms.service.CategoryService;
import com.yaza.cms.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class CategoryImpl implements CategoryService {

    @Resource
    private CategoryRepo repo;

    @Resource
    private UserService userService;


    @Override
    public Category getByName(String name) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public Category get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Category t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        repo.save(t);
    }

    @Override
    public List<Category> getPageable() {
        return null;
    }

    @Override
    public Category save(Category t) {
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
    public Category findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(Category current, Category old) {
        return null;
    }
}
