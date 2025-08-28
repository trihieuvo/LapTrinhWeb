package com.baitap02.login.dao;

import java.util.List;
import com.baitap02.login.model.Category;

public interface CategoryDao {
    void insert(Category category);
    void edit(Category category);
    void delete(int cate_id, int userid);
    Category get(int cate_id, int userid);
    List<Category> getAll(int userid);
}