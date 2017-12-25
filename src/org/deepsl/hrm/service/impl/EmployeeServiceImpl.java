package org.deepsl.hrm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.deepsl.hrm.dao.EmployeeDao;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.service.EmployeeService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		
		if(employee.getName()!=null&& !employee.getName().equals("")){
			employee.setName("%"+employee.getName()+"%");
		}
		if(employee.getPhone()!=null&& !employee.getPhone().equals("")){
			employee.setPhone("%"+employee.getPhone()+"%");
		}
		if(employee.getCardId()!=null&& !employee.getCardId().equals("")){
			employee.setCardId("%"+employee.getCardId()+"%");
		}
		
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("employee", employee);
		
		Integer count = employeeDao.count(hashMap);
		pageModel.setRecordCount(count);
		hashMap.put("pageModel", pageModel);
		
		List<Employee> selectByPage = employeeDao.selectByPage(hashMap);
		return selectByPage;
	}

	@Override
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		Employee selectById = employeeDao.selectById(id);
		return selectById;
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public void modifyEmployee(Employee employee) {
		employeeDao.update(employee);
	}

}
