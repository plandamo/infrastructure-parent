package com.mountain.infrastructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mountain.infrastructure.config.CrowdConstant;
import com.mountain.infrastructure.exception.LoginFailedException;
import com.mountain.infrastructure.mapper.AdminMapper;
import com.mountain.infrastructure.model.Admin;
import com.mountain.infrastructure.model.AdminExample;
import com.mountain.infrastructure.service.AdminService;
import com.mountain.infrastructure.util.CrowdUtil;
import com.mountain.infrastructure.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @ProjectName: infrastructure-parent
 * @Package: com.mountain.infrastructure.service.impl
 * @ClassName: AdminServiceImpl
 * @Author: liujinshan
 * @Description:
 * @Date: 2020/3/19 15:41
 * @Version: 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {


    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;


    public void saveAdmin(Admin admin) {

        adminMapper.insert(admin);

    }


    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }


    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        // 1.根据登录账号查询Admin对象
        // ①创建AdminExample对象
        AdminExample adminExample = new AdminExample();

        // ②创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();

        // ③在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        // ④调用AdminMapper的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);

        // 2.判断Admin对象是否为null
        if(list == null || list.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if(list.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);

        // 3.如果Admin对象为null则抛出异常
        if(admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.如果Admin对象不为null则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密

        if (!Md5Util.checkpassword(userPswd,userPswdDB)){
           throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }


        // 8.如果一致则返回Admin对象
        return admin;
    }


    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1.调用PageHelper的静态方法开启分页功能
        // 这里充分体现了PageHelper的“非侵入式”设计：原本要做的查询不必有任何修改
        PageHelper.startPage(pageNum, pageSize);

        // 2.执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<Admin>(list);
    }


    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }


    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }


    public void update(Admin admin) {

        // “Selective”表示有选择的更新，对于null值的字段不更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常全类名="+e.getClass().getName());

/*            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }*/
        }
    }

}
