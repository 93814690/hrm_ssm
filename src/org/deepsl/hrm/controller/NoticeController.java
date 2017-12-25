package org.deepsl.hrm.controller;

import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.NoticeService;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version V1.0
 * @Description: 处理公告请求控制器
 */

@Controller
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /**
     *公告查询
     *
     */
    @RequestMapping("selectNotice")
    public String selectNotice(Integer pageIndex, String title, String content, Model model) {
        if (pageIndex == null)
            pageIndex = 1;

        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Notice> notices = noticeService.listNoticeByPage(pageModel, title, content);
        model.addAttribute("notices", notices);
        model.addAttribute("pageModel",pageModel);
        return "notice/notice";
    }

    /**
     *公告添加页面/添加公告
     *
     */
    @RequestMapping("addNotice")
    public String addNotice(int flag, Notice notice, HttpSession session) {
        if (flag == 1) {
            return "notice/showAddNotice";
        }
//        User loginUser = (User) session.getAttribute(HrmConstants.USER_SESSION);
//
//        notice.setUid(loginUser.getId());
        noticeService.saveNotice(notice);
        return "redirect:/notice/selectNotice";
    }

    /**
     *删除公告
     *
     */
    @RequestMapping("removeNotice")
    public String removeNotice(int[] ids) {

        noticeService.removeNoticeByIds(ids);

        return "redirect:/notice/selectNotice";
    }

    /**
     *公告更新页面/更新公告
     *
     */
    @RequestMapping("updateNotice")
    public String updateNotice(int flag, Notice notice, Model model) {
        if (flag == 1) {
            Notice noticeById = noticeService.getNoticeById(notice.getId());
            model.addAttribute("notice", noticeById);
            return "notice/showUpdateNotice";
        }

        noticeService.updateNotice(notice);
        return "redirect:/notice/selectNotice";
    }

    /**
     *公告预览
     *
     */

    @RequestMapping("previewNotice")
    public String previewNotice(int id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "notice/previewNotice";
    }

}
