package org.deepsl.hrm.controller;

import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理登录请求
     *
     * @param loginname 登录名
     * @param password  密码
     * @return 跳转的视图
     */
    @RequestMapping(value = "login")
    public ModelAndView login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv) {
        // 调用业务逻辑组件判断用户是否可以登录
        User user = hrmService.login(loginname, password);
        if (user != null) {
            // 将用户保存到HttpSession当中
            session.setAttribute(HrmConstants.USER_SESSION, user);
            // 客户端跳转到main页面
            mv.setViewName("redirect:/main");
        } else {
            // 设置登录失败提示信息
            mv.addObject("message", "登录名或密码错误!请重新输入");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/loginForm");
        }
        return mv;

    }

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
        List<User> users = hrmService.findUser(user, pageModel);
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
        hrmService.addUser(user);

        return "redirect:/user/selectUser";
    }

    @RequestMapping("updateUser")
    public String updateUser(int flag, User user, Model model) {
        if (flag == 1) {
            User userById = hrmService.findUserById(user.getId());
            model.addAttribute("user", userById);
            return "user/showUpdateUser";
        }
        hrmService.modifyUser(user);
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

        hrmService.removeUserByIds(ids);

        return "forward:selectUser";

    }


}
