package com.baitap02.login.service;

import java.util.List;
import com.baitap02.login.dao.CategoryDao;
import com.baitap02.login.dao.CategoryDaoImpl;
import com.baitap02.login.model.Category;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category category) {
		categoryDao.edit(category);
	}

	@Override
	public void delete(int cate_id, int userid) {
		categoryDao.delete(cate_id, userid);
	}

	@Override
	public Category get(int cate_id, int userid) {
		return categoryDao.get(cate_id, userid);
	}

	@Override
	public List<Category> getAll(int userid) {
		return categoryDao.getAll(userid);
	}
}