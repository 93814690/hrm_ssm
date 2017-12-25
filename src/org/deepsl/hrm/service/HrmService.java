package org.deepsl.hrm.service;

import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.util.tag.PageModel;

import java.util.List;

/**
 * @version V1.0
 * @Description: 人事管理服务层接口
 */
public interface HrmService {


    /**
     * 用户登录
     *
     * @param loginname
     * @param password
     * @return User对象
     */
    User login(String loginname, String password);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return 用户对象
     */
    User findUserById(Integer id);

    /**
     * 获得所有用户
     *
     * @return User对象的List集合
     */
    List<User> findUser(User user, PageModel pageModel);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void removeUserById(Integer id);


    /**
     * 根据ids删除用户
     *
     * @param ids
     */
    void removeUserByIds(int[] ids);

    /**
     * 修改用户
     *
     * @param user 用户对象
     */
    void modifyUser(User user);

    /**
     * 添加用户
     *
     * @param user 用户对象
     */
    void addUser(User user);


}
