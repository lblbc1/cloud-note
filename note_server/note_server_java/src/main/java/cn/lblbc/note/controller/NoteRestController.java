package cn.lblbc.note.controller;

import cn.lblbc.login.bean.response.Resp;
import cn.lblbc.login.utils.JwtUtils;
import cn.lblbc.note.bean.Note;
import cn.lblbc.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@RestController
@RequestMapping("/note")
public class NoteRestController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private JwtUtils jwtUtils;

    @DeleteMapping(value = "/notes/{id}")
    public Resp<String> deleteNote(@PathVariable long id) {
        noteService.del(id);
        return new Resp<>();
    }

    @PostMapping(value = "/notes")
    public Resp<String> addNote(@RequestBody Note note, @RequestHeader("Authorization") String authorization) {
        if (authorization != null) {
            long userId = jwtUtils.getUserIdFromToken(authorization);
            noteService.add(userId, note.getContent());
        }
        return new Resp<>();
    }

    @PutMapping(value = "/notes/{id}")
    public Resp<String> modifyNote(@PathVariable long id, @RequestBody Note note) {
        noteService.modify(id, note.getContent());
        return new Resp<>();
    }

    @GetMapping("/notes")
    public Resp<List<Note>> list(@RequestHeader("Authorization") String authorization) {
        Resp<List<Note>> resp = new Resp<>();
        if (authorization != null) {
            long userId = jwtUtils.getUserIdFromToken(authorization);
            resp.setData(noteService.queryByUserId(userId));
        }
        return resp;
    }

    @GetMapping("/notes/{id}")
    public Resp<Note> query(@PathVariable long id) {
        Resp<Note> resp = new Resp<>();
        resp.setData(noteService.query(id));
        return resp;
    }

}
