package com.mountain.infrastructure.service;

import java.util.List;
import java.util.Map;
import com.mountain.infrastructure.model.Auth;

public interface AuthService {

	List<Auth> getAll();

	List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

	void saveRoleAuthRelathinship(Map<String, List<Integer>> map);
	
	List<String> getAssignedAuthNameByAdminId(Integer adminId);

}
