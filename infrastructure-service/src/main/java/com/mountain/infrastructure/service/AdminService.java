package com.mountain.infrastructure.service;

import com.github.pagehelper.PageInfo;
import com.mountain.infrastructure.model.Admin;

import java.util.List;

/**
 * @ProjectName: infrastructure-parent
 * @Package: com.mountain.infrastructure.service
 * @ClassName: AdminService
 * @Author: liujinshan
 * @Description:
 * @Date: 2020/3/19 15:40
 * @Version: 1.0
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void remove(Integer adminId);

    Admin getAdminById(Integer adminId);

    void update(Admin admin);
}
