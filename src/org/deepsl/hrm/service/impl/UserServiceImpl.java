package org.deepsl.hrm.service.impl;

import org.deepsl.hrm.dao.UserDao;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.service.UserService;
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
@Service
public class UserServiceImpl implements UserService {

    /**
     * 自动注入持久层Dao对象
     */
    @Autowired
    private UserDao userDao;

    /**
     * HrmServiceImpl接口findUser方法实现
     *
     * @see { HrmService }
     */
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {

        Map<String, Object> params = new HashMap<>();
        String username = user.getUsername();
        Integer status = user.getStatus();
        if (username != null && !username.isEmpty())
            params.put("username", "%" + username + "%");
        if (status != null)
            params.put("status", status);
        Integer count = userDao.count(params);
        if (count <= 0)
            return null;
        pageModel.setRecordCount(count);
        int limit = pageModel.getPageSize();
        params.put("limit", limit);
        int offset = pageModel.getFirstLimitParam();
        params.put("offset", offset);
        List<User> users = userDao.listByPage(params);

        return users;
    }

    /**
     * HrmServiceImpl接口findUserById方法实现
     *
     * @see { HrmService }
     */
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDao.getById(id);
    }

    /**
     * HrmServiceImpl接口removeUserById方法实现
     *
     * @see { HrmService }
     */
    @Override
    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }

    /**
     * HrmServiceImpl接口addUser方法实现
     *
     * @see { HrmService }
     */
    @Override
    @Transactional
    public void modifyUser(User user) {
        userDao.update(user);

    }

    /**
     * HrmServiceImpl接口modifyUser方法实现
     *
     * @see { HrmService }
     */
    @Override
    @Transactional
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void removeUserByIds(int[] ids) {
        userDao.deleteByIds(ids);
    }

    /*****************部门服务接口实现*************************************/

    /*****************员工服务接口实现*************************************/


    /*****************职位接口实现*************************************/


    /*****************公告接口实现*************************************/


    /*****************文件接口实现*************************************/


}
