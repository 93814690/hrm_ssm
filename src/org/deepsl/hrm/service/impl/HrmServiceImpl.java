package org.deepsl.hrm.service.impl;

import org.deepsl.hrm.dao.UserDao;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Description: 人事管理系统服务层接口实现类
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {

    /**
     * 自动注入持久层Dao对象
     */
    @Autowired
    private UserDao userDao;


    /*****************用户服务接口实现*************************************/
    /**
     * HrmServiceImpl接口login方法实现
     *
     * @see { HrmService }
     */
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("loginname", loginname);
        hashMap.put("password", password);


        return userDao.getByLoginnameAndPassword(hashMap);
    }
}
