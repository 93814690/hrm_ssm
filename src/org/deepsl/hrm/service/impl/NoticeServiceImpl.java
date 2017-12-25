package org.deepsl.hrm.service.impl;

import org.deepsl.hrm.dao.NoticeDao;
import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.service.NoticeService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    @Transactional
    public void saveNotice(Notice notice) {
        notice.setCreateDate(new Date());
        noticeDao.save(notice);
    }

    @Override
    @Transactional
    public void updateNotice(Notice notice) {
        noticeDao.update(notice);
    }


    @Override
    @Transactional
    public void removeNoticeByIds(int[] ids) {
        noticeDao.deleteByIds(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice getNoticeById(int id) {
        return noticeDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notice> listNoticeByPage(PageModel pageModel, String title, String content) {

        Map<String, Object> params = new HashMap<>();
        if (title != null && !title.isEmpty())
            params.put("title", "%" + title + "%");
        if (content != null && !content.isEmpty())
            params.put("content", "%" + content + "%");
        Integer count = noticeDao.count(params);
        if (count <= 0)
            return null;
        pageModel.setRecordCount(count);
        int limit = pageModel.getPageSize();
        params.put("limit", limit);
        int offset = pageModel.getFirstLimitParam();
        params.put("offset", offset);
        List<Notice> notices = noticeDao.listByPage(params);
        return notices;
    }
}
