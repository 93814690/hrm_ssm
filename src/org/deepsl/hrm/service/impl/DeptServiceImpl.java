package org.deepsl.hrm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.deepsl.hrm.dao.DeptDao;
import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.service.DeptService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("name", dept.getName());
		int count = deptDao.count(params).intValue();
		pageModel.setRecordCount(count);
		params.put("limit", pageModel.getPageSize());
		params.put("offset", count == 0 ? 0 : pageModel.getFirstLimitParam());
		List<Dept> depts = deptDao.selectByPage(params);
		return depts;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		return deptDao.selectAllDept();
	}

	@Transactional
	@Override
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);
	}

	@Transactional
	@Override
	public void addDept(Dept dept) {
		deptDao.save(dept);
	}

	@Transactional(readOnly=true)
	@Override
	public Dept findDeptById(Integer id) {
		return deptDao.selectById(id);
	}

	@Transactional
	@Override
	public void modifyDept(Dept dept) {
		deptDao.update(dept);
	}
}
