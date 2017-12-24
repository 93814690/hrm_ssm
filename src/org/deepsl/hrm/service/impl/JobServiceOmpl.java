package org.deepsl.hrm.service.impl;

import org.deepsl.hrm.dao.JobDao;
import org.deepsl.hrm.domain.*;
import org.deepsl.hrm.service.OtherServiceInterface;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceOmpl implements OtherServiceInterface {

    @Autowired
    private JobDao jobDao;

    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        return null;
    }

    @Override
    public void removeEmployeeById(Integer id) {

    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return null;
    }

    @Override
    public void addEmployee(Employee employee) {

    }

    @Override
    public void modifyEmployee(Employee employee) {

    }

    @Override
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        return null;
    }

    @Override
    public List<Dept> findAllDept() {
        return null;
    }

    @Override
    public void removeDeptById(Integer id) {

    }

    @Override
    public void addDept(Dept dept) {

    }

    @Override
    public Dept findDeptById(Integer id) {
        return null;
    }

    @Override
    public void modifyDept(Dept dept) {

    }

    @Override
    public List<Job> findAllJob() {

        return jobDao.selectAllJob();
    }

    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {

        Map<String, Object> map = new HashMap<>();
        if (job.getName() != null) {
            job.setName("%"+job.getName()+"%");
        }
        map.put("job", job);
        Integer count = jobDao.count(map);
        pageModel.setRecordCount(count);
        map.put("model", pageModel);
        List<Job> jobs = jobDao.selectByPage(map);

        return jobs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeJobById(Integer id) {
        jobDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addJob(Job job) {
        jobDao.save(job);
    }

    @Override
    public Job findJobById(Integer id) {
        return jobDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyJob(Job job) {
        jobDao.update(job);
    }

    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        return null;
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return null;
    }

    @Override
    public void removeNoticeById(Integer id) {

    }

    @Override
    public void addNotice(Notice notice) {

    }

    @Override
    public void modifyNotice(Notice notice) {

    }

    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        return null;
    }

    @Override
    public void addDocument(Document document) {

    }

    @Override
    public Document findDocumentById(Integer id) {
        return null;
    }

    @Override
    public void removeDocumentById(Integer id) {

    }

    @Override
    public void modifyDocument(Document document) {

    }
}
