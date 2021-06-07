package cn.xzc.dao;

import java.util.List;

import cn.xzc.domain.Category;

public interface CategoryDao {

	void add(Category c);

	Category find(String id);

	List<Category> getAll();

}

