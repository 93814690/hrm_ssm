package org.deepsl.hrm.service;

import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.util.tag.PageModel;

import java.util.List;

public interface NoticeService {

    void saveNotice(Notice notice);

    void updateNotice(Notice notice);

    void removeNoticeByIds(int[] ids);

    Notice getNoticeById(int id);

    List<Notice> listNoticeByPage(PageModel pageModel, String title, String content);

}
