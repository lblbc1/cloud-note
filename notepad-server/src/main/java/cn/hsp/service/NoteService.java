package cn.hsp.service;

import cn.hsp.bean.Note;
import cn.hsp.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Service
public class NoteService {
    @Autowired
    private NoteMapper noteMapper;

    public Note query(long noteId) {
        return noteMapper.query(noteId);
    }

    public void add(long userId,String title, String content) {
        noteMapper.add(userId, title, content);
    }

    public void modify(long id, String title, String content) {
        noteMapper.modify(id, title, content);
    }

    public void delete(long id) {
        noteMapper.del(id);
    }

    public List<Note> queryByUserId(long userId) {
        return noteMapper.queryByUserId(userId);
    }
}
