package cn.lblbc.note.mapper;

import cn.lblbc.note.bean.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Repository
public interface NoteMapper {
    @Select(value = "select * from note where id = #{id}")
    Note query(@Param("id") long id);

    @Select(value = "select * from note where user_id = #{userId}")
    List<Note> queryByUserId(@Param("userId") long userId);

    @Insert(value = "insert into note(user_id,content) values (#{userId}, #{content})")
    void add(@Param("userId") long userId, @Param("content") String content);

    @Update(value = "update note set content=#{content} where id = #{id}")
    void modify(@Param("id") long id, @Param("content") String content);

    @Delete("delete from note where id = #{id}")
    void del(@Param("id") long id);
}
