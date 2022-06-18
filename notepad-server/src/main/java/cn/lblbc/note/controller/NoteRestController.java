package cn.lblbc.note.controller;

import cn.lblbc.note.bean.Note;
import cn.lblbc.login.bean.response.Resp;
import cn.lblbc.login.utils.JwtUtils;
import cn.lblbc.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteRestController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping(value = "del/{id}")
    public Resp<String> delData(@PathVariable long id) {
        noteService.delete(id);
        return new Resp<>();
    }

    @PostMapping(value = "add")
    public Resp<String> addData(@RequestBody Note note, @RequestHeader("Authorization") String authorization) {
        final String authTokenPrefix = "Bearer ";
        long userId = 0;
        if (authorization != null && authorization.startsWith(authTokenPrefix)) {
            String token = authorization.substring(authTokenPrefix.length());
            userId = jwtUtils.getUserIdFromToken(token);
        }
        noteService.add(userId, note.getContent());
        return new Resp<>();
    }

    @PostMapping(value = "modify")
    public Resp<String> modifyData(@RequestBody Note note) {
        noteService.modify(note.getId(), note.getContent());
        return new Resp<>();
    }

    @GetMapping("list")
    public Resp<List<Note>> list(@RequestHeader("Authorization") String authorization) {
        Resp<List<Note>> resp = new Resp<>();
        int userId = getUserIdFromHeader(authorization);
        resp.setData(noteService.queryByUserId(userId));
        return resp;
    }

    @GetMapping("query/{noteId}")
    public Resp<Note> query(@PathVariable long noteId) {
        Resp<Note> resp = new Resp<>();
        resp.setData(noteService.query(noteId));
        return resp;
    }

    private int getUserIdFromHeader(String authorization) {
        final String authTokenPrefix = "Bearer ";
        int userId = 0;
        if (authorization != null && authorization.startsWith(authTokenPrefix)) {
            String token = authorization.substring(authTokenPrefix.length());
            userId = jwtUtils.getUserIdFromToken(token);
        }
        return userId;
    }
}
