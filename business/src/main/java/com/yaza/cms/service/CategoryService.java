package com.yaza.cms.service;

import com.yaza.cms.domain.config.Category;

import java.util.List;

public interface CategoryService extends GenericNameService<Category> {

    public List<Category> findByQueryType(String type);

}
