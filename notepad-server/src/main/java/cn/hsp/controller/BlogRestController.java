package cn.hsp.controller;

import cn.hsp.bean.Blog;
import cn.hsp.login.bean.response.Resp;
import cn.hsp.login.utils.JwtUtils;
import cn.hsp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogRestController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping(value = "api/del/{id}")
    public Resp<String> delBlog(@PathVariable long id) {
        blogService.del(id);
        return new Resp<>();
    }

    @PostMapping(value = "api/add")
    public Resp<String> addBlog(@RequestBody Blog blog, @RequestHeader("Authorization") String authorization) {
        final String authTokenPrefix = "Bearer ";
        long userId = 0;
        if (authorization != null && authorization.startsWith(authTokenPrefix)) {
            String token = authorization.substring(authTokenPrefix.length());
            userId = jwtUtils.getUserIdFromToken(token);
        }
        blogService.add(userId,blog.getTitle(), blog.getContent());
        return new Resp<>();
    }

    @PostMapping(value = "api/modify")
    public Resp<String> modifyBlog(@RequestBody Blog blog) {
        blogService.modify(blog.getId(), blog.getTitle(), blog.getContent());
        return new Resp<>();
    }

    @GetMapping("api/list/{userId}")
    public Resp<List<Blog>> list(@PathVariable long userId) {
        Resp<List<Blog>> resp = new Resp<>();
        resp.setData(blogService.queryByUserId(userId));
        return resp;
    }

    @GetMapping("api/query/{blogId}")
    public Resp<Blog> query(@PathVariable long blogId) {
        Resp<Blog> resp = new Resp<>();
        resp.setData(blogService.query(blogId));
        return resp;
    }

}
