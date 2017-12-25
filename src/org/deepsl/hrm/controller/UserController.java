package org.deepsl.hrm.controller;

import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.UserService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * 处理用户请求控制器
 */
@Controller
@RequestMapping("user")
public class UserController {

    /**
     * 自动注入UserService
     */
    @Autowired
    private UserService userService;



    /**
     * 处理查询请求
     *
     * @param pageIndex 请求的是第几页
     * @param user      模糊查询参数
     * @param model     model
     */
    @RequestMapping("selectUser")
    public String selectUser(Integer pageIndex, User user, Model model) {

        pageIndex = (pageIndex == null) ? 1 : pageIndex;
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<User> users = userService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("pageModel",pageModel);
        return "user/user";
    }

    /**
     * 处理添加用户请求
     *
     * @param flag
     * @param user 用户信息
     * @return 跳转的视图
     */
    @RequestMapping("addUser")
    public String addUser(Integer flag, User user) {
        if (flag == 1) {
            return "user/showAddUser";
        }
        user.setCreateDate(new Date());
        userService.addUser(user);
        return "redirect:/user/selectUser";
    }

    @RequestMapping("updateUser")
    public String updateUser(int flag, User user, Model model) {
        if (flag == 1) {
            User userById = userService.findUserById(user.getId());
            model.addAttribute("user", userById);
            return "user/showUpdateUser";
        }
        userService.modifyUser(user);
        return "redirect:/user/selectUser";
    }

    /**
     * 处理删除用户请求
     *
     * @param ids 需要删除的id字符串
     * @param
     */
    @RequestMapping("removeUser")
    public String removeUser(int[] ids) {
        userService.removeUserByIds(ids);
        return "forward:selectUser";
    }

}
