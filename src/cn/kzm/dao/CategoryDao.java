package cn.kzm.dao;

import cn.kzm.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getALLCats()throws Exception;

    void addCategory(Category category)throws Exception;
}
