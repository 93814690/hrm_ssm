package org.deepsl.hrm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.service.DeptService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @Description: 处理部门请求控制器
 * @author   
 * @version V1.0   
 */
@RequestMapping("dept")
@Controller
public class DeptController {
	
	@Autowired
	DeptService deptService;

	@RequestMapping("selectDept")
	public String selectDept(Model model,PageModel pageModel,Dept dept){
		List<Dept> depts = deptService.findDept(dept, pageModel);
		model.addAttribute("depts",depts);
//		model.addAttribute("pageModel", pageModel);
		return "dept/dept";
	}
	
	@RequestMapping("addDept")
	public void addDept(Dept dept, int flag, HttpServletRequest request,HttpServletResponse response) {
		try {
			if (flag == 1) {
				request.getRequestDispatcher("/WEB-INF/jsp/dept/showAddDept.jsp").forward(request,response);
				return;
			}
			deptService.addDept(dept);
			response.sendRedirect("/hrm_ssm/dept/selectDept");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping("updateDept")
	public void updateDept(Dept dept,Integer flag,HttpServletRequest request,HttpServletResponse response){
		try {
			if (flag == 1) {
				dept = deptService.findDeptById(dept.getId());
				request.setAttribute("dept", dept);
				request.getRequestDispatcher("/WEB-INF/jsp/dept/showUpdateDept.jsp").forward(request,response);
				return;
			}
			deptService.modifyDept(dept);
			response.sendRedirect("/hrm_ssm/dept/selectDept");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("removeDept")
	public void removeDept(Integer[] ids,HttpServletRequest request,HttpServletResponse response){
		for (Integer id : ids) {
			deptService.removeDeptById(id);
		}
		try {
			response.sendRedirect("/hrm_ssm/dept/selectDept");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
