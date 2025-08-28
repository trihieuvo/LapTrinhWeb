package com.baitap02.login.service;

import java.util.List;

import com.baitap02.login.model.Category;

public interface CategoryService {
	void insert(Category category);

	void edit(Category category);

	void delete(int cate_id, int userid);

	Category get(int cate_id, int userid);

	List<Category> getAll(int userid);
}
