package com.mountain.infrastructure.service;

import java.util.List;

import com.mountain.infrastructure.model.Menu;

public interface MenuService {
	
	List<Menu> getAll();

	void saveMenu(Menu menu);

	void updateMenu(Menu menu);

	void removeMenu(Integer id);

}
