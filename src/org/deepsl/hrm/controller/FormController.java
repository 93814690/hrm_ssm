package org.deepsl.hrm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Description:  
 * @date 2015年8月13日 下午8:30:37 
 * @version V1.0   
 */

/**
 * 动态页面跳转控制器
 * */
@Controller
public class FormController{

	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// 动态跳转页面
		return formName;
	}

	@RequestMapping(value="main")
	public String mainForm(HttpServletRequest request){
		// 动态跳转页面
		String ctx = request.getContextPath();
		HttpSession session = request.getSession();
		session.setAttribute("ctx",ctx);
		return "main";
	}

	@RequestMapping(value="left")
	public String leftForm(){
		// 动态跳转页面
		return "left";
	}

	@RequestMapping(value="right")
	public String rightForm(){
		// 动态跳转页面
		return "right";
	}

	@RequestMapping(value="top")
	public String topForm(){
		// 动态跳转页面
		return "top";
	}

}

