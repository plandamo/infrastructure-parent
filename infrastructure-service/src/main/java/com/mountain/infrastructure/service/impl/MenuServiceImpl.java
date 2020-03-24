package com.mountain.infrastructure.service.impl;

import java.util.List;

import com.mountain.infrastructure.mapper.MenuMapper;
import com.mountain.infrastructure.model.Menu;
import com.mountain.infrastructure.model.MenuExample;
import com.mountain.infrastructure.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;


	public List<Menu> getAll() {
		return menuMapper.selectByExample(new MenuExample());
	}


	public void saveMenu(Menu menu) {
		menuMapper.insert(menu);
	}


	public void updateMenu(Menu menu) {
		
		// 由于pid没有传入，一定要使用有选择的更新，保证“pid”字段不会被置空
		menuMapper.updateByPrimaryKeySelective(menu);
	}


	public void removeMenu(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}

}
