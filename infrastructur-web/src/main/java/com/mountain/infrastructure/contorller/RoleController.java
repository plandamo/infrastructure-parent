package com.mountain.infrastructure.contorller;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mountain.infrastructure.model.Role;
import com.mountain.infrastructure.service.RoleService;
import com.mountain.infrastructure.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	/**
	 * @Author liujinshan
	 * @Version  1.0
	 * @Description 批量删除
	 * @param roleIdList
	 * @Return com.mountain.infrastructure.util.ResultEntity<java.lang.String>
	 * @Date 2020/3/24 21:18
	 */
	@ResponseBody
	@RequestMapping("/role/remove/by/role/id/array.json")
	public ResultEntity<String> removeByRoleIdAarry(@RequestBody List<Integer> roleIdList) {
		
		roleService.removeRole(roleIdList);
		
		return ResultEntity.successWithoutData();
	}

	/**
	 * @Author liujinshan
	 * @Version  1.0
	 * @Description 更新角色
	 * @param role
	 * @Return com.mountain.infrastructure.util.ResultEntity<java.lang.String>
	 * @Date 2020/3/24 21:18
	 */
	@ResponseBody
	@RequestMapping("/role/update.json")
	public ResultEntity<String> updateRole(Role role) {
		
		roleService.updateRole(role);
		
		return ResultEntity.successWithoutData();
	}

	/**
	 * @Author liujinshan
	 * @Version  1.0
	 * @Description 新增角色
	 * @param role
	 * @Return com.mountain.infrastructure.util.ResultEntity<java.lang.String>
	 * @Date 2020/3/24 21:18
	 */
	@ResponseBody
	@RequestMapping("/role/save.json")
	public ResultEntity<String> saveRole(Role role) {
		
		roleService.saveRole(role);
		
		return ResultEntity.successWithoutData();
	}



	/**
	 * @Author liujinshan
	 * @Version  1.0
	 * @Description 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @Return com.mountain.infrastructure.util.ResultEntity<com.github.pagehelper.PageInfo<com.mountain.infrastructure.model.Role>>
	 * @Date 2020/3/24 21:19
	 */
	@ResponseBody
	@RequestMapping("/role/get/page/info.json")
	public ResultEntity<PageInfo<Role>> getPageInfo(
				@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
				@RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
				@RequestParam(value="keyword", defaultValue="") String keyword
			) {
		
		// 调用Service方法获取分页数据
		PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
		
		// 封装到ResultEntity对象中返回（如果上面的操作抛出异常，交给异常映射机制处理）
		return ResultEntity.successWithData(pageInfo);
	}

}
