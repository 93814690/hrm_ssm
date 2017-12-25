package org.deepsl.hrm.controller;

import java.util.List;

import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.service.OtherServiceInterface;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 * @Description: 处理职位请求控制器
 */

@RequestMapping("/job")
@Controller
public class JobController {


    @Autowired
    @Qualifier("jobServiceOmpl")
    OtherServiceInterface service;

    @RequestMapping("/selectJob")
    public String selectJob(Job job, PageModel model, HttpServletRequest request) {

        List<Job> jobs = service.findJob(job, model);
        request.setAttribute("jobs", jobs);
        request.setAttribute("pageModel", model);


        return "job/job";
    }

    @RequestMapping("/addJob")
    public String addJob(int flag, Job job) {

        if (flag == 1) {
            return "job/showAddJob";
        }
        if (flag == 2) {
            service.addJob(job);
            return "redirect:selectJob";
        }


        return "XXX";
    }

    @RequestMapping("updateJob")
    public String updateJob(int flag, Job job, HttpServletRequest request) {

        if (flag == 1) {
            Job jobById = service.findJobById(job.getId());
            request.setAttribute("job", jobById);
            return "job/showUpdateJob";
        }
        if (flag == 2) {
            service.modifyJob(job);
            return "redirect:selectJob";
        }

        return "XXX";
    }

    @RequestMapping("removeJob")
    public String removeJob(int[] ids) {

        for (int id : ids) {
            service.removeJobById(id);
        }

        return "redirect:selectJob";
    }
}
