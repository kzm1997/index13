package cn.kzm.service;

import cn.kzm.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCats()throws Exception;

    void addCategory(Category category)throws Exception;
}
