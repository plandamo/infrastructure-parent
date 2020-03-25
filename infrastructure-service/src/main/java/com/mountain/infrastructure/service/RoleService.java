package com.mountain.infrastructure.service;

import com.github.pagehelper.PageInfo;
import com.mountain.infrastructure.model.Role;
import java.util.List;


public interface RoleService {

    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
