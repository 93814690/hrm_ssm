package org.deepsl.hrm.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.service.DeptService;
import org.deepsl.hrm.service.EmployeeService;
import org.deepsl.hrm.service.OtherServiceInterface;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 处理员工请求控制器
 * @version V1.0
 */

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	DeptService deptService;
	@Autowired
	@Qualifier("jobServiceOmpl")
	OtherServiceInterface jobServiceOmpl;

	@RequestMapping("selectEmployee")
	public String selectEmployee(Employee employee, PageModel pageModel,
			HttpServletRequest request) {

		System.out.println("EmployeeController.selectEmployee()=" + employee+"--name="+employee.getName());
		try {

			String job_id = request.getParameter("job_id");
			String dept_id = request.getParameter("dept_id");
			if (dept_id != null && !dept_id.equals("")) {
				Dept dept = new Dept();
				dept.setId(Integer.parseInt(dept_id));
				employee.setDept(dept);
			}
			if (job_id != null && !job_id.equals("")) {
				Job job = new Job();
				job.setId(Integer.parseInt(job_id));
				employee.setJob(job);
			}

			List<Employee> selectEmployee = employeeService.findEmployee(
					employee, pageModel);
			request.setAttribute("employees", selectEmployee);
			request.setAttribute("pageModel", pageModel);
			
			List<Dept> findAllDept = deptService.findAllDept();
			List<Job> findAllJob = jobServiceOmpl.findAllJob();
			request.setAttribute("depts", findAllDept);
			request.setAttribute("jobs", findAllJob);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "employee/employee";
	}

	@RequestMapping("addEmployee")
	public String addEmployee(Employee employee,Integer flag, HttpServletRequest request) {
		if(flag==1){
			
			List<Dept> findAllDept = deptService.findAllDept();
			List<Job> findAllJob = jobServiceOmpl.findAllJob();
			request.setAttribute("depts", findAllDept);
			request.setAttribute("jobs", findAllJob);
			return "employee/showAddEmployee";
		}
		if(flag==2){
			
			String job_id = request.getParameter("job_id");
			String dept_id = request.getParameter("dept_id");
			if (dept_id != null && !dept_id.equals("")) {
				Dept dept = new Dept();
				dept.setId(Integer.parseInt(dept_id));
				employee.setDept(dept);
			}else{
				System.out.println("EmployeeController.addEmployee() 没选部门");
				return "employee/showAddEmployee";
			}
			if (job_id != null && !job_id.equals("")) {
				Job job = new Job();
				job.setId(Integer.parseInt(job_id));
				employee.setJob(job);
			}else{
				System.out.println("EmployeeController.addEmployee() 没选职位");
				return "employee/showAddEmployee";
			}
			
			employeeService.addEmployee(employee);
			return "redirect:/employee/selectEmployee";
		}
		return "";
	}

	@RequestMapping("updateEmployee")
	public String updateEmployee(Employee employee,Integer flag,
			HttpServletRequest request,Model model){

		try {
			if(flag==1){
				employee = employeeService.findEmployeeById(employee.getId());
				
				SimpleDateFormat dateFormat=new SimpleDateFormat( "yyyy-MM-dd "); 
				String format = dateFormat.format(employee.getBirthday());

				model.addAttribute("employee", employee);
				
				List<Dept> findAllDept = deptService.findAllDept();
				List<Job> findAllJob = jobServiceOmpl.findAllJob();
				request.setAttribute("depts", findAllDept);
				request.setAttribute("jobs", findAllJob);
				
				return "employee/showUpdateEmployee";
			}
			if(flag==2){

				String job_id = request.getParameter("job_id");
				String dept_id = request.getParameter("dept_id");
				if (dept_id != null && !dept_id.equals("")) {
					Dept dept = new Dept();
					dept.setId(Integer.parseInt(dept_id));
					employee.setDept(dept);
				}else{
					System.out.println("EmployeeController.updateEmployee() 没选部门");
					return "employee/showUpdateEmployee";
				}
				if (job_id != null && !job_id.equals("")) {
					Job job = new Job();
					job.setId(Integer.parseInt(job_id));
					employee.setJob(job);
				}else{
					System.out.println("EmployeeController.updateEmployee() 没选职位");
					return "employee/showUpdateEmployee";
				}
				employeeService.modifyEmployee(employee);
				return "redirect:/employee/selectEmployee";
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("removeEmployee")
	public String removeEmployee(Integer[] ids){
		
		for (int i = 0; i < ids.length; i++) {
			employeeService.removeEmployeeById(ids[i]);
		}
		return "redirect:/employee/selectEmployee";
	}

}
