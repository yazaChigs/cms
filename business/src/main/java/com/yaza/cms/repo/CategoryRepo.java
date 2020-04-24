package com.yaza.cms.repo;

import com.yaza.cms.domain.config.Category;

import java.util.List;

public interface CategoryRepo extends AbstractNameDescRepo<Category, Long> {

    public List<Category> findByQueryType(String type);

}
