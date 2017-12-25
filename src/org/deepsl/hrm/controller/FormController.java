package org.deepsl.hrm.controller;

import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.common.HrmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * @Description:
 * @date 2015年8月13日 下午8:30:37
 * @version V1.0
 */

/**
 * 动态页面跳转控制器
 */
@Controller
public class FormController {

    @Autowired
    private HrmService hrmService;

    @RequestMapping(value = "/{formName}")
    public String loginForm(@PathVariable String formName) {
        // 动态跳转页面

        return formName;
    }

    /**
     * 处理登录请求
     *
     * @param loginname 登录名
     * @param password  密码
     * @return 跳转的视图
     */
    @RequestMapping("login")
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

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute(HrmConstants.USER_SESSION);
        session.invalidate();
        return "loginForm";
    }

}

