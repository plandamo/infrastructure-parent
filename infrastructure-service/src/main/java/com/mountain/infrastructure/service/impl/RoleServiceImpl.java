package com.mountain.infrastructure.service.impl;

import java.util.List;
import com.mountain.infrastructure.mapper.RoleMapper;
import com.mountain.infrastructure.model.Role;
import com.mountain.infrastructure.model.RoleExample;
import com.mountain.infrastructure.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;


	public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
		
		// 1.开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		
		// 2.执行查询
		List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
		
		// 3.封装为PageInfo对象返回
		return new PageInfo<Role>(roleList);
	}


	public void saveRole(Role role) {
		roleMapper.insert(role);
	}


	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKey(role);
	}


	public void removeRole(List<Integer> roleIdList) {
		
		RoleExample example = new RoleExample();
		
		RoleExample.Criteria criteria = example.createCriteria();
		
		//delete from t_role where id in (5,8,12)
		criteria.andIdIn(roleIdList);
		
		roleMapper.deleteByExample(example);
	}

}
