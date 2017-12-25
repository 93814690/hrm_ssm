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

}
