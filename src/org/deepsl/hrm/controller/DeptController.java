package org.deepsl.hrm.controller;

import java.util.List;

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
		return "dept/dept";
	}
	
	@RequestMapping("addDept")
	public String addDept(Dept dept, Integer flag) {
		if (flag == 1) {
			return "forward:/WEB-INF/jsp/dept/showAddDept.jsp";
		}
		deptService.addDept(dept);
		return "redirect:/dept/selectDept";
	}
	
	@RequestMapping("updateDept")
	public String updateDept(Integer flag, Dept dept, Model model) {
		if (flag == 1) {
			dept = deptService.findDeptById(dept.getId());
			model.addAttribute("dept", dept);
			return "forward:/WEB-INF/jsp/dept/showUpdateDept.jsp";
		}
		deptService.modifyDept(dept);
		return "redirect:/dept/selectDept";
	}
	
	
	@RequestMapping("removeDept")
	public String removeDept(Integer[] ids){
		for (Integer id : ids) {
			deptService.removeDeptById(id);
		}
		return "forward:selectDept";		
	}
	
}
